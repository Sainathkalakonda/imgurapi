package com.sync.img.imgurapi.Services;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sync.img.imgurapi.Repository.ImageRepository;
import com.sync.img.imgurapi.Repository.ImgurUserRepository;
import com.sync.img.imgurapi.model.Image;
import com.sync.img.imgurapi.model.ImgrUser;
import com.sync.img.imgurapi.model.ImgurImageResponse;
import com.sync.img.imgurapi.model.UserImages;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
    public class ImgurService {

        @Value("${imgur.access.token}")
        private String accessToken;

        @Autowired
        private ImgurUserRepository imgurUserRepository;

        @Autowired
        private ImageRepository imageRepository;


    private ObjectMapper objectMapper = new ObjectMapper();

        public List<ImgurImageResponse> uploadImage(List<File> imageFiles,String userName) throws JsonProcessingException {

            List<ImgurImageResponse> uploadrespone=new ArrayList<>();
            try {

                ImgrUser user = imgurUserRepository.findByUsername(userName);

                if (user == null) {
                    throw new IllegalArgumentException("User not found");
                }

                for (File imageToUpload : imageFiles) {
                    HttpResponse<String> response = Unirest.post("https://api.imgur.com/3/upload")
                            .header("Authorization", "Bearer " + accessToken)
                            .field("image", imageToUpload)
                            .asString();
                    System.out.println(response.toString());
                    if (response.getStatus() == 200) {
                        ImgurImageResponse imageData = objectMapper.readValue(response.getBody(), ImgurImageResponse.class);
                        Image image = new Image();
                        image.setDeleteHash(imageData.getData().getDeletehash());
                        image.setLink(imageData.getData().getLink());
                        image.setUsername(userName);
                        image.setUser(user);
                        imageRepository.save(image);
                        uploadrespone.add(imageData);
                    } else {
                        throw new RuntimeException("Upload failed: " + response.getStatusText());
                    }
                }
            } catch (UnirestException e) {
                throw new RuntimeException("Upload failed", e);
            }
            return uploadrespone;
        }

        @Transactional
        public String deleteImage(String deleteHash, String username) {
            try {

                ImgrUser user = imgurUserRepository.findByUsername(username);
                Image image=imageRepository.findByDeleteHash(deleteHash);
                if (user == null) {
                    throw new IllegalArgumentException("User not found");
                }
                if (image == null) {
                    throw new IllegalArgumentException("Hash not found");
                }

                HttpResponse<String> response = Unirest.delete("https://api.imgur.com/3/image/" + deleteHash)
                        .header("Authorization", "Bearer " + accessToken)
                        .asString();
                if (response.getStatus() == 200)   {
                    imageRepository.deleteByDeleteHash(deleteHash);
                    return "Image deleted successfully.";
                } else {
                    throw new RuntimeException("Delete failed: " + response.getStatusText());
                }
            } catch (UnirestException e) {
                throw new RuntimeException("Delete failed", e);
            }
            catch(IllegalArgumentException e){
                throw e;
            }
            catch (Exception e) {
                throw new RuntimeException("Delete failed", e);
            }
        }

        private String parseImageUrl(String responseBody) {
            String marker = "\"link\":\"";
            int markerPosition = responseBody.indexOf(marker);
            if (markerPosition >= 0) {
                int startIndex = markerPosition + marker.length();
                int endIndex = responseBody.indexOf("\"", startIndex);
                if (endIndex >= 0) {
                    return responseBody.substring(startIndex, endIndex);
                }
            }
            throw new RuntimeException("Failed to parse response");
        }

    public UserImages showImages(String username) {

        List<Image> images = imageRepository.findImagesByUsername(username);
        ImgrUser user = imgurUserRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        UserImages userImages=new UserImages();
        List<String> imagesUrls=new ArrayList<>();
        user.setPassword(null);
        userImages.setUserName(username);
        userImages.setEmail(user.getEmail());
        userImages.setFirstName(user.getFirstName());
        userImages.setLastName(user.getLastName());
        if (!images.isEmpty()) {
            images.stream().forEach(userImage -> {
                imagesUrls.add(userImage.getLink());
            });
            userImages.setImages(imagesUrls);
        }

        return userImages;
    }
}





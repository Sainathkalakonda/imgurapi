package com.sync.img.imgurapi.Services;

import com.sync.img.imgurapi.Repository.ImageRepository;
import com.sync.img.imgurapi.Repository.ImgurUserRepository;
import com.sync.img.imgurapi.model.Image;
import com.sync.img.imgurapi.model.ImgrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImgurUserRepository imgurUserRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImgurService imageService;

    public Image saveImage(String url, String deleteHash, Long userId) {
        ImgrUser imageUser = imgurUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ImageUser not found"));

        Image image = new Image();
        image.setUrl(url);
        image.setDeleteHash(deleteHash);
        image.setUser(imageUser);
        return imageRepository.save(image);
    }
}
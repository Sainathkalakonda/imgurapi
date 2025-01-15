package com.sync.img.imgurapi.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sync.img.imgurapi.Services.ImgurService;
import com.sync.img.imgurapi.model.ImgurImageResponse;
import com.sync.img.imgurapi.model.UserImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/imgur")
public class ImgurController {

    @Autowired
    private ImgurService imgurService;

    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile[] files,@RequestParam String username) throws IOException {
        List<File> filesList = new ArrayList<>();
        List<ImgurImageResponse> imagesresponse=new ArrayList<>();
        try {
            for (MultipartFile fileItem : files) {
                File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileItem.getOriginalFilename());
                fileItem.transferTo(convFile);
                filesList.add(convFile);
            }
           imagesresponse=imgurService.uploadImage(filesList,username);
        } catch (IllegalArgumentException e ) {
           return e.getMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong! Please try again";
        }
        return  mapper.writeValueAsString(imagesresponse);

    }

    @DeleteMapping("/delete/{deleteHash}")
    public String deleteImage(@PathVariable String deleteHash, @RequestParam String username) throws IOException {
        String deletedInfo;
        try {
            deletedInfo = imgurService.deleteImage(deleteHash, username);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong! Please try again";
        }

        return deletedInfo;
    }

    @GetMapping("/showbyUser")
    public String showImages(@RequestParam String username) throws IOException {
        UserImages imgeWithUser;
        try {
            imgeWithUser = imgurService.showImages(username);
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong! Please try again";
        }

        return mapper.writeValueAsString(imgeWithUser);
    }
}


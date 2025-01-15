package com.sync.img.imgurapi.Controllers;

import com.sync.img.imgurapi.Services.ImgurService;
import com.sync.img.imgurapi.model.ImgurImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/imgur")
public class ImgurController {

    @Autowired
    private ImgurService imgurService;

    @PostMapping("/upload")
    public ImgurImageResponse uploadImage(@RequestParam("file") MultipartFile file,@RequestParam String username) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        return imgurService.uploadImage(convFile,username);
    }

    @DeleteMapping("/delete/{deleteHash}")
    public String deleteImage(@PathVariable String deleteHash,@RequestParam String username) throws IOException {
        return imgurService.deleteImage(deleteHash,username);
    }

    @DeleteMapping("/showbyUser")
    public String deleteImage(@RequestParam String username) throws IOException {
        return imgurService.showimages(username).toString();
    }
}


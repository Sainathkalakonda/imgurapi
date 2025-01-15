package com.sync.img.imgurapi.Services;

import com.sync.img.imgurapi.Repository.ImgurUserRepository;
import com.sync.img.imgurapi.model.ImgrUser;
import com.sync.img.imgurapi.model.ImgrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ImgurUserRepository imgurUserRepository;

    public ImgrUser registerUser(ImgrUser imageUser) {
        if (imgurUserRepository.findByUsername(imageUser.getUsername())!=null) {
            throw new RuntimeException("Username already exists");
        }
        return imgurUserRepository.save(imageUser);
    }
}

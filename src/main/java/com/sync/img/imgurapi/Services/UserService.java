package com.sync.img.imgurapi.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sync.img.imgurapi.Repository.ImgurUserRepository;
import com.sync.img.imgurapi.model.ImgrUser;
import com.sync.img.imgurapi.model.ImgrUser;
import com.sync.img.imgurapi.model.LoginCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    ObjectMapper objectmapper=new ObjectMapper();

    @Autowired
    private ImgurUserRepository imgurUserRepository;

    public String registerUser(ImgrUser imageUser) throws JsonProcessingException {
        if (imgurUserRepository.findByUsername(imageUser.getUsername())!=null) {
            return "Username already exists";
        }
        return objectmapper.writeValueAsString(imgurUserRepository.save(imageUser)) ;
    }

    public String checkUser(LoginCheck user) {
        ImgrUser imageUser = imgurUserRepository
                .findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (imageUser == null) {
            return "Invalid username or password";
        }
        else
        {
            return "User logged in";
        }

    }
}

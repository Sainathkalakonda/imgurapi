package com.sync.img.imgurapi.Controllers;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.sync.img.imgurapi.Services.UserService;
import com.sync.img.imgurapi.model.ImgrUser;
import com.sync.img.imgurapi.model.LoginCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ImgrUser imageUser) throws JsonProcessingException {
        return ResponseEntity.ok(userService.registerUser(imageUser));
    }
    @PostMapping("/login")
    public ResponseEntity<String> register(@RequestBody LoginCheck user) throws JsonProcessingException {
        return ResponseEntity.ok(userService.checkUser(user));
    }

}


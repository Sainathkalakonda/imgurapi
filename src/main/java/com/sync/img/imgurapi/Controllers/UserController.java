package com.sync.img.imgurapi.Controllers;



import com.sync.img.imgurapi.Services.UserService;
import com.sync.img.imgurapi.model.ImgrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ImgrUser> register(@RequestBody ImgrUser imageUser) {
        return ResponseEntity.ok(userService.registerUser(imageUser));
    }

}


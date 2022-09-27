package com.the_ajou.web.controller;

import com.the_ajou.service.UserService;
import com.the_ajou.web.dto.user.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @CrossOrigin
    @PostMapping("/user/signup")
    public void signupUser(@RequestBody UserCreateDTO userCreateDTO){
        userService.signUp(userCreateDTO);
    }
}

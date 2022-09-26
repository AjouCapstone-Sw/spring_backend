package com.the_ajou.web.controller;

import com.the_ajou.service.UserService;
import com.the_ajou.web.dto.user.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @CrossOrigin
    @PostMapping("/user/signup")
    public void signupUser(@RequestBody UserCreateDTO userCreateDTO){
        userService.signUp(userCreateDTO);
    }
}

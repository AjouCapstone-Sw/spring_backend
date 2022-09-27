package com.the_ajou.web.controller;

import com.the_ajou.domain.user.User;
import com.the_ajou.service.UserService;
import com.the_ajou.web.dto.user.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public void signupUser(@RequestBody UserCreateDTO userCreateDTO){
        userService.signUp(userCreateDTO);
    }

    @GetMapping("/user")
    public Optional<User> findUserById(int id) {
        return (Optional<User>) userService.findUserById(id);
    }
}

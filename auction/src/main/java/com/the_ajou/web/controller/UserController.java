package com.the_ajou.web.controller;

import com.the_ajou.domain.user.User;
import com.the_ajou.service.UserService;
import com.the_ajou.web.dto.user.UserCreateDTO;
import com.the_ajou.web.dto.user.UserLoginDTO;
import com.the_ajou.web.dto.user.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public int signupUser(@RequestBody UserCreateDTO userCreateDTO){
        return userService.signUp(userCreateDTO);
    }

    @GetMapping("/user/id")
    public User findUserById(@RequestParam int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/user/login")
    public int login(@RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO);
    }

    @PostMapping("/user/change-password")
    public void changePassword(String email, String newPassword){
        userService.changePassword(email, newPassword);
    }

    @PatchMapping("/user/update")
    public int updateUser(@RequestParam int id, @RequestBody UserUpdateDTO userUpdateDTO){
        return userService.updateUser(id, userUpdateDTO);
    }

    @PatchMapping("/user/delete")
    public int deleteUser(int id){
        return userService.deleteUser(id);
    }
}

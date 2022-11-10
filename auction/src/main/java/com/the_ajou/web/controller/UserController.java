package com.the_ajou.web.controller;

import com.the_ajou.domain.user.User;
import com.the_ajou.service.UserService;
import com.the_ajou.web.dto.user.UserAddressUpdateDTO;
import com.the_ajou.web.dto.user.UserCreateDTO;
import com.the_ajou.web.dto.user.UserLoginDTO;
import com.the_ajou.web.dto.user.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/auth/signup")
    public int signupUser(@RequestBody UserCreateDTO userCreateDTO){
        return userService.signUp(userCreateDTO);
    }

    @GetMapping("/user/id")
    public User findUserById(@RequestParam int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/api/v1/auth/login")
    public int login(@RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO);
    }

    @PostMapping("/api/v1/auth/pw")
    public void changePassword(@RequestBody UserLoginDTO userLoginDTO){
        userService.changePassword(userLoginDTO);
    }

    @PatchMapping("/user/update")
    public boolean updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        return userService.updateUser(userUpdateDTO);
    }

    @PatchMapping("/user/delete")
    public int deleteUser(int id){
        return userService.deleteUser(id);
    }

    @GetMapping("/api/v1/auth/searchId")
    public boolean findEmail(String email){
        return userService.findEmail(email);
    }

    @PatchMapping("/api/v1/auction/address")
    public boolean updateAddress(@RequestBody UserAddressUpdateDTO userAddressUpdateDTO){
        return userService.updateAddress(userAddressUpdateDTO);
    }
}

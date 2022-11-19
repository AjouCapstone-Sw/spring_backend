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

    @PostMapping("/auth/signup")
    public int signupUser(@RequestBody UserCreateDTO userCreateDTO){
        return userService.signUp(userCreateDTO);
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/auth/login")
    public int login(@RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO);
    }

    @PostMapping("/auth/pw")
    public void changePassword(@RequestBody UserLoginDTO userLoginDTO){
        userService.changePassword(userLoginDTO);
    }

    @PatchMapping("/user/update")
    public boolean updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        return userService.updateUser(userUpdateDTO);
    }

    @PatchMapping("/user/delete/{id}")
    public int deleteUser(@PathVariable("id") int id){
        return userService.deleteUser(id);
    }

    @GetMapping("/auth/search/{email}")
    public boolean findEmail(@PathVariable("email") String email){
        return userService.findEmail(email);
    }

    @PatchMapping("/user/registerAddress")
    public boolean updateAddress(@RequestBody UserAddressUpdateDTO userAddressUpdateDTO){
        return userService.updateAddress(userAddressUpdateDTO);
    }
}

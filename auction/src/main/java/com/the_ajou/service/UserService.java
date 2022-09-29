package com.the_ajou.service;

import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dto.user.UserCreateDTO;
import com.the_ajou.web.dto.user.UserLoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public int signUp(UserCreateDTO userCreateDTO){
        User checkUser = userRepository.findByEmail(userCreateDTO.getEmail());

        if(checkUser == null){
            User user = User.builder()
                    .userId(userCreateDTO.getUserId())
                    .email(userCreateDTO.getEmail())
                    .password(userCreateDTO.getPassword())
                    .birth(userCreateDTO.getBirth())
                    .gender(userCreateDTO.getGender())
                    .build();

            userRepository.save(user);
            return 1;
        }else{
            return 0;
        }
    }

    @Transactional
    public Object findUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user == null){
            return 0;
        }else{
            return user;
        }
    }

    @Transactional
    public int login(UserLoginDTO userLoginDTO){
        User user = userRepository.findByEmail(userLoginDTO.getEmail());
        if(user.getPassword().equals(userLoginDTO.getPassword())){
            System.out.println("OK");
            return 1;
        }else{
            System.out.println("FAIL");
            return 0;
        }
    }

    @Transactional
    public void changePassword(String email, String newPassword){
        User user = userRepository.findByEmail(email);
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}

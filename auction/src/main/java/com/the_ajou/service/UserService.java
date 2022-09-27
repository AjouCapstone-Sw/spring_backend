package com.the_ajou.service;

import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dto.user.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signUp(UserCreateDTO userCreateDTO){
        User user = User.builder()
                .userId(userCreateDTO.getUserId())
                .email(userCreateDTO.getEmail())
                .password(userCreateDTO.getPassword())
                .birth(userCreateDTO.getBirth())
                .gender(userCreateDTO.getGender())
                .build();

        userRepository.save(user);
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
}

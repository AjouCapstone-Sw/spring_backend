package com.the_ajou.service;

import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dto.user.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signUp(UserCreateDTO userCreateDTO){
        userRepository.save(
                User.builder()
                        .userId(userCreateDTO.getUserId())
                        .userName(userCreateDTO.getUserName())
                        .email(userCreateDTO.getEmail())
                        .build()
        );
    }
}

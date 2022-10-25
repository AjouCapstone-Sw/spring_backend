package com.the_ajou.service;

import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dto.user.UserCreateDTO;
import com.the_ajou.web.dto.user.UserLoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Transactional
    public int signUp(UserCreateDTO userCreateDTO){
        User checkUser = userRepository.findByEmail(userCreateDTO.getEmail());
        User nickNameCheck = userRepository.findBynickname(userCreateDTO.getNickname());

        if(checkUser == null && nickNameCheck == null){
            User user = User.builder()
                    .email(userCreateDTO.getEmail())
                    .password(userCreateDTO.getPassword())
                    .phoneNum(userCreateDTO.getPhoneNum())
                    .address(userCreateDTO.getAddress())
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .status(1)
                    .nickname(userCreateDTO.getNickname())
                    .point(0)
                    .gender(userCreateDTO.getGender())
                    .birth(userCreateDTO.getBirth())
                    .build();

            userRepository.save(user);
            return user.getId();
        }else if(nickNameCheck != null){
            return -2;
        }
        else{
            return -1;
        }
    }

    @Transactional
    public User findUserById(int id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 사용자 입니다"));
        return user;
    }

    @Transactional
    public int login(UserLoginDTO userLoginDTO){
        User user = userRepository.findByEmail(userLoginDTO.getEmail());
        //if(passwordEncoder.matches(user.getPassword(), userLoginDTO.getPassword())){
        if(user.getPassword().equals(userLoginDTO.getPassword())){
            System.out.println("OK");
            return 1;
        }else{
            System.out.println("FAIL");
            return 0;
        }
    }


    public void changePassword(String email, String newPassword){
        User user = userRepository.findByEmail(email);
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}

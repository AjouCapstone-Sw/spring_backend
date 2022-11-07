package com.the_ajou.service;

import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dto.user.UserCreateDTO;
import com.the_ajou.web.dto.user.UserLoginDTO;
import com.the_ajou.web.dto.user.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                    .name(userCreateDTO.getName())
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
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 사용자 입니다"));
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

    @Transactional
    public void changePassword(String email, String newPassword){
        User user = userRepository.findByEmail(email);
        user.setPassword(newPassword);
        user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        userRepository.save(user);
    }

    @Transactional
    public int updateUser(int id, UserUpdateDTO userUpdateDTO){
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));
        User nickCheck = userRepository.findBynickname(userUpdateDTO.getNickname());

        if(nickCheck != null)
            return -1;


        user.updateUser(userUpdateDTO);

        return id;
    }
}

package com.the_ajou.service;
;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dao.user.UserLoginDAO;
import com.the_ajou.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public int signUp(UserCreateDTO userCreateDTO){
        User checkUser = userRepository.findByEmail(userCreateDTO.getEmail());
        User nickNameCheck = userRepository.findBynickName(userCreateDTO.getNickName());

        if(checkUser == null && nickNameCheck == null){
            User user = User.builder()
                    .email(userCreateDTO.getEmail())
                    .password(passwordEncoder.encode(userCreateDTO.getPassword()))
                    .phoneNum(userCreateDTO.getPhoneNum())
                    .address(userCreateDTO.getAddress())
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .status('N')
                    .nickName(userCreateDTO.getNickName())
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
    public UserLoginDAO login(UserLoginDTO userLoginDTO){
        User user = userRepository.findByEmail(userLoginDTO.getEmail());

        //System.out.println("----------------" + user.getEmail() + "----------------------");

        if(passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())){
            return UserLoginDAO.builder()
                    .userId(user.getId())
                    .nickName(user.getNickName())
                    .build();
        }else{
            return UserLoginDAO.builder()
                    .userId(0)
                    .nickName("")
                    .build();
        }
    }

    @Transactional
    public boolean changePassword(UserLoginDTO userLoginDTO){
        User user = userRepository.findByEmail(userLoginDTO.getEmail());

        if(user != null){
            user.setPassword(passwordEncoder.encode(userLoginDTO.getPassword()));
            user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateUser(UserUpdateDTO userUpdateDTO){
        User user = userRepository.findById(userUpdateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));
        User nickCheck = userRepository.findBynickName(userUpdateDTO.getNickName());

        if(nickCheck != null)
            return false;
        user.updateUser(userUpdateDTO);

        return true;
    }

    @Transactional
    public int deleteUser(int id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));
        user.setStatus('Y');

        return user.getId();
    }

    @Transactional
    public String findEmail(UserFindDTO userFindDTO) {
        User user = userRepository.findBynickName(userFindDTO.getNickName());

        if(user != null && Objects.equals(user.getBirth(), userFindDTO.getBirth()))
            return user.getEmail();

        return "fail";
    }

    @Transactional
    public boolean updateAddress(UserAddressUpdateDTO userAddressUpdateDTO){
        User user = userRepository.findById(userAddressUpdateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        user.setAddress(userAddressUpdateDTO.getAddress());

        return !Objects.equals(user.getAddress(), "");
    }

    @Transactional
    public int getUserId(String nickName){
        User user = userRepository.findBynickName(nickName);
        if(user != null)
            return user.getId();
        else
            return 0;
    }
}

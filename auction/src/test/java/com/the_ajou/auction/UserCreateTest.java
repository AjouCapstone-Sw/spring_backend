package com.the_ajou.auction;

import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.service.UserService;
import com.the_ajou.web.dto.user.UserCreateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserCreateTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void UserCreateTest(){

        for(int i=0;i<10;i++){
            //UserCreateDTO userCreateDTO = new UserCreateDTO("USER"+i,"user"+i+".com");
            //userService.signUp(userCreateDTO);
        }
        
    }
}

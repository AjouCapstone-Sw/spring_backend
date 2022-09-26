package com.the_ajou.auction;

import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserCreateTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void UserCreateTest(){
        for(int i=0;i<10;i++){
            User user = new User(i, "user"+i, "userEmail@"+i+".com");
            userRepository.save(
                    User.builder()
                            .userId(i)
                            .email("userEmail"+i+".com")
                            .userName("user"+i)
                            .build());
        }
        
    }
}

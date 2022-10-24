package com.the_ajou.auction;

import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserCreateTest {

    private final UserRepository userRepository;

    public UserCreateTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    @Transactional
    void UserFindTest(){
        User user = userRepository.findByEmail("kimhb09@ajou.ac.kr");
        Assertions.assertThat(user.getPassword()).isSameAs("1234");
    }
}

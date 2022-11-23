package com.the_ajou.auction;

import com.the_ajou.domain.interests.Interest;
import com.the_ajou.domain.interests.InterestRepository;
import com.the_ajou.domain.product.Product;
import com.the_ajou.domain.product.ProductRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dto.interest.InterestCreateDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@WebAppConfiguration
class InterestServiceTest {
    @Autowired
    private InterestRepository interestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Rollback
    @Test
    void createInterest(){
        User user = userRepository.findById(1)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Product product = productRepository.findById(1)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 상품입니다."));

        Interest interest = Interest.builder()
                .user(user)
                .product(product)
                .build();
        interestRepository.save(interest);

        Assertions.assertThat(interest).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void deleteInterest(){
        Interest interest = interestRepository.findById(1)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 관심 등록입니다."));

        interestRepository.delete(interest);

        Assertions.assertThat(interest).isNotNull();
    }
}

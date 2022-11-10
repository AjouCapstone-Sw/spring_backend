package com.the_ajou.service;

import com.the_ajou.domain.interests.Interest;
import com.the_ajou.domain.interests.InterestRepository;
import com.the_ajou.domain.product.Product;
import com.the_ajou.domain.product.ProductRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dto.interest.InterestCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InterestService {
    private final InterestRepository interestRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public boolean createInterest(InterestCreateDTO interestCreateDTO){
        User user = userRepository.findById(interestCreateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Product product = productRepository.findById(interestCreateDTO.getProjectId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 상품입니다."));

        Interest interest = Interest.builder()
                .user(user)
                .product(product)
                .build();
        interestRepository.save(interest);

        return interest.getId() != -1;
    }

    @Transactional
    public boolean deleteInterest(int interestId){
        Interest interest = interestRepository.findById(interestId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 관심 등록입니다."));

        interestRepository.delete(interest);

        Interest tempInterest = interestRepository.findById(interestId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 관심 등록입니다."));

        return tempInterest == null;
    }
}

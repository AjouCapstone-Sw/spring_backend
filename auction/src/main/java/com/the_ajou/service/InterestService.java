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
        User userCheck = userRepository.findById(interestCreateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Product productCheck = productRepository.findById(interestCreateDTO.getProductId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 상품입니다."));

        Interest existingInterest = interestRepository.findByProductIdAndUserId(interestCreateDTO.getProductId(), interestCreateDTO.getUserId());

        if(userCheck != null && productCheck != null){
            if(existingInterest == null){
                Interest interest = Interest.builder()
                        .user(userCheck)
                        .product(productCheck)
                        .build();
                interestRepository.save(interest);

            }else{
                interestRepository.delete(existingInterest);
            }
            return true;
        }
        return false;
    }
}

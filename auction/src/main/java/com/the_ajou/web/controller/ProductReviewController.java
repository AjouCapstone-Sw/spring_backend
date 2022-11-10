package com.the_ajou.web.controller;

import com.the_ajou.service.ProductReviewService;
import com.the_ajou.web.dao.productReview.ProductReviewDAO;
import com.the_ajou.web.dto.productReview.ProductReviewCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductReviewController {
    private final ProductReviewService productReviewService;

    @PostMapping("/api/v1/review/product")
    private boolean createReview(@RequestBody ProductReviewCreateDTO productReviewCreateDTO){
        return productReviewService.createProductReview(productReviewCreateDTO);
    }

    @GetMapping("/api/v1/review/product")
    private List<ProductReviewDAO> getProductReviewByUserId(int userId){
        return productReviewService.getReviewByUserId(userId);
    }

}

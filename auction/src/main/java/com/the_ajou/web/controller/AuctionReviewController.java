package com.the_ajou.web.controller;

import com.the_ajou.domain.auctionReview.AuctionReview;
import com.the_ajou.service.AuctionReviewService;
import com.the_ajou.web.dao.auctionReview.AuctionReviewDAO;
import com.the_ajou.web.dto.auctionReview.AuctionReviewCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuctionReviewController {
    private final AuctionReviewService auctionReviewService;

    @PostMapping("/api/v1/review/auction")
    private boolean createReview(@RequestBody AuctionReviewCreateDTO auctionReviewCreateDTO){
        return auctionReviewService.createAuctionReview(auctionReviewCreateDTO);
    }

    @GetMapping("/api/v1/review/auction")
    private List<AuctionReviewDAO> getReviews(int userId){
        return auctionReviewService.getReviewByUserId(userId);
    }

}

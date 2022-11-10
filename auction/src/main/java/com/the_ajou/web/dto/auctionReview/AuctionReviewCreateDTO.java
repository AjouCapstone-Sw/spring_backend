package com.the_ajou.web.dto.auctionReview;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuctionReviewCreateDTO {
    private int userId;
    private String review;
    private int score;

    @Builder
    AuctionReviewCreateDTO(int userId, String review, int score, String createdAt){
        this.userId = userId;
        this.review = review;
        this.score = score;
    }
}

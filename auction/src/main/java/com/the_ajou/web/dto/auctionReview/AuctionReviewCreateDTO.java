package com.the_ajou.web.dto.auctionReview;

import com.the_ajou.domain.auctionReview.AuctionReviewRepository;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuctionReviewCreateDTO {
    private int userId;
    private String review;
    private int score;

    public AuctionReviewCreateDTO() {

    }

    @Builder
    AuctionReviewCreateDTO(int userId, String review, int score, String createdAt){
        this.userId = userId;
        this.review = review;
        this.score = score;
    }
}

package com.the_ajou.domain.auctionReview;

import com.the_ajou.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionReviewRepository extends JpaRepository<AuctionReview, Integer> {
    List<AuctionReview> findByUser(User user);
}

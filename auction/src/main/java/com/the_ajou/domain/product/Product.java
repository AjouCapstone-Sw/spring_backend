package com.the_ajou.domain.product;


import com.the_ajou.domain.category.Category;
import com.the_ajou.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "duration")
    private int duration;

    @Column(name = "bidPrice")
    private int bidPrice;

    @Column(name = "startPrice")
    private int startPrice;

    @Column(name = "instant")
    private int instant;

    @Column(name = "endPrice")
    private int endPrice;

    @Column(name = "createdAt")
    private String createdAt;

    @Column(name = "updatedAt")
    private String updatedAt;

    @Column(name = "status")
    private char status;

    @Column(name = "buyerId")
    private int buyerId;

    @Column(name = "productImg1")
    private String productImage1 = "";

    @Column(name = "productImg2")
    private String productImage2 = "";

    @Column(name = "productImg3")
    private String productImage3 = "";

    @Column(name = "productImg4")
    private String productImage4 = "";

    @Column(name = "productImg5")
    private String productImage5 = "";

    @Builder
    Product(User user, Category category, String title, String description, String startTime, int duration,int startPrice, int bidPrice,
            int instant, int endPrice, String createdAt, String updatedAt, char status, int buyerId,
            String productImage1, String productImage2, String productImage3, String productImage4, String productImage5
    ){
        this.user = user;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.duration = duration;
        this.startPrice = startPrice;
        this.instant = instant;
        this.bidPrice = bidPrice;
        this.endPrice = endPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.buyerId = buyerId;
        this.productImage1 = productImage1;
        this.productImage2 = productImage2;
        this.productImage3 = productImage3;
        this.productImage4 = productImage4;
        this.productImage5 = productImage5;
    }
}

package com.the_ajou.domain.sale;


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
@Entity(name = "sale")
public class Sale {
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

    @Column(name = "content")
    private String content;

    @Column(name = "startAt")
    private String startAt;

    @Column(name = "startPrice")
    private int startPrice;

    @Column(name = "field")
    private int field;

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

    @Column(name = "sale_img1")
    private String saleImage1 = "";

    @Column(name = "sale_img2")
    private String saleImage2 = "";

    @Column(name = "sale_img3")
    private String saleImage3 = "";

    @Column(name = "sale_img4")
    private String saleImage4 = "";

    @Column(name = "sale_img5")
    private String saleImage5 = "";

    @Builder
    Sale(User user, Category category, String title, String content, String startAt, int startPrice,
         int field, int endPrice, String createdAt, String updatedAt, char status, int buyerId,
        String saleImage1, String saleImage2, String saleImage3, String saleImage4, String saleImage5
    ){
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.startAt = startAt;
        this.startPrice = startPrice;
        this.field = field;
        this.endPrice = endPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.buyerId = buyerId;
        this.saleImage1 = saleImage1;
        this.saleImage2 = saleImage2;
        this.saleImage3 = saleImage3;
        this.saleImage4 = saleImage4;
        this.saleImage5 = saleImage5;
    }
}

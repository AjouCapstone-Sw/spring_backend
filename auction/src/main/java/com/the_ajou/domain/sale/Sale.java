package com.the_ajou.domain.sale;


import com.the_ajou.domain.category.Category;
import com.the_ajou.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "sprice")
    private int sPrice;

    @Column(name = "field")
    private int field;

    @Column(name = "eprice")
    private int ePrice;

    @Column(name = "createdAt")
    private String createdAt;

    @Column(name = "updatedAt")
    private String updatedAt;

    @Column(name = "status")
    private char status;

    @Column(name = "buyerId")
    private int buyerId;

    @Builder
    Sale(User user, Category category, String title, String content, String startAt, int sPrice, int field, int ePrice, String createdAt, String updatedAt, char status, int buyerId){
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.startAt = startAt;
        this.sPrice = sPrice;
        this.field = field;
        this.ePrice = ePrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.buyerId = buyerId;
    }
}

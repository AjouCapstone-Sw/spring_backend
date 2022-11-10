package com.the_ajou.domain.point;

import com.the_ajou.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "payment_history")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "purchaseId")
    private int purchaseId;

    @Column(name = "createdAt")
    private String createdAt;

    @Column(name = "type")
    private char type;

    @Column(name = "pointChange")
    private int pointChange;

    @Builder
    Point(User user, int purchaseId, String createdAt, char type, int pointChange){
        this.user = user;
        this.purchaseId = purchaseId;
        this.createdAt = createdAt;
        this.type = type;
        this.pointChange = pointChange;
    }
}

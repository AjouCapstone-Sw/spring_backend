package com.the_ajou.domain.purchase;

import com.the_ajou.domain.sale.Sale;
import com.the_ajou.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "saleId")
    private Sale sale;

    @Column(name = "purchaseAddress")
    private String purchaseAddress;

    @Column(name = "createdAt")
    private String createdAt;

    @Column(name = "updatedAt")
    private String updatedAt;

    @Column(name = "status")
    private char status;

    @Builder
    Purchase(User user, Sale sale, String purchaseAddress, String createdAt, String updatedAt, char status){
        this.user = user;
        this.sale = sale;
        this.purchaseAddress = purchaseAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}

package com.the_ajou.domain.payment_history;

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
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "payment")
    private int payment;

    @Column(name = "date")
    private String date;

    @Builder
    PaymentHistory(User user, int payment, String date){
        this.user = user;
        this.payment = payment;
        this.date = date;
    }
}

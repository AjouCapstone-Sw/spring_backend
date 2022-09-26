package com.the_ajou.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "name" , length = 20)
    private String userName;

    @Column(name = "email" , length = 20)
    private String email;


    @Builder
    public User(int userId, String userName, String email){
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}

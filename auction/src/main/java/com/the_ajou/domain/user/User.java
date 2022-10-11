package com.the_ajou.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phoneNum")
    private String phoneNum;

    @Column(name = "address")
    private String address;

    @Column(name = "createdAt")
    private Timestamp createdAt;

    @Column(name = "updatedAt")
    private Timestamp updatedAt;

    @Column(name = "status")
    private int status;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "point")
    private int point;


    @Builder
    public User(String email, String password, String phoneNum, String address, Timestamp createdAt, Timestamp updatedAt, int status, String nickname, int point){
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.nickname = nickname;
        this.point = point;
    }
}

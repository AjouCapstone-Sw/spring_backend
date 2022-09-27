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
    private int id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birth")
    private String birth;

    @Column(name = "gender")
    private String gender;

    @Builder
    public User(int id, String userId, String email, String password, String birth, String gender){
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
    }
}

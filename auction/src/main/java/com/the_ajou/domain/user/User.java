package com.the_ajou.domain.user;

import com.the_ajou.web.dto.user.UserUpdateDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Column(name = "status")
    private int status;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "point")
    private int point;

    @Column(name = "createdAt")
    private String createdAt;

    @Column(name = "updatedAt")
    private String updatedAt;

    @Column(name = "gender")
    private char gender;

    @Column(name = "birth")
    private String birth;

    @Column(name = "name")
    private String name;


    @Builder
    public User(String email, String password, String phoneNum, String address, String createdAt, String updatedAt, int status, String nickname, int point, char gender, String birth, String name){
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.nickname = nickname;
        this.point = point;
        this.gender = gender;
        this.birth = birth;
        this.name = name;
    }

    public void updatePoint(int point){
        this.point = this.point + point;
    }

    public void updateUser(UserUpdateDTO userUpdateDTO){

        if(userUpdateDTO.getPhoneNum() != null)
            this.phoneNum = userUpdateDTO.getPhoneNum();
        if(userUpdateDTO.getAddress() != null)
            this.address = userUpdateDTO.getAddress();
        if(userUpdateDTO.getNickname() != null)
            this.nickname = userUpdateDTO.getNickname();
        if(userUpdateDTO.getGender() != ' ')
            this.gender = userUpdateDTO.getGender();
        if(userUpdateDTO.getBirth() != null)
            this.birth = userUpdateDTO.getBirth();

        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

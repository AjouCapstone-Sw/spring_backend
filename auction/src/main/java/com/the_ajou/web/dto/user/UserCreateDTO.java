package com.the_ajou.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Date;
import java.sql.Timestamp;


@Getter
@NoArgsConstructor
public class UserCreateDTO {
    private String email;
    private String password;
    private String phoneNum;
    private String address;
    private String nickname;
    private char gender;
    private Date birth;


    @Builder
    public UserCreateDTO(String email, String password, String phoneNum, String address, String nickname, char gender, Date birth){
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.address = address;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = birth;
    }
}

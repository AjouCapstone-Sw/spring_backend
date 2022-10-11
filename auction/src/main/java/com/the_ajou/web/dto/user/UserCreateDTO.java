package com.the_ajou.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Getter
@NoArgsConstructor
public class UserCreateDTO {
    private String email;
    private String password;
    private String phoneNum;
    private String address;
    private String nickname;


    @Builder
    public UserCreateDTO(String email, String password, String phoneNum, String address, String nickname){
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.address = address;
        this.nickname = nickname;
    }
}

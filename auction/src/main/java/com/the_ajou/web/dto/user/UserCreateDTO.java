package com.the_ajou.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class UserCreateDTO {
    private String userId;
    private String email;
    private String password;
    private String birth;
    private String gender;


    @Builder
    public UserCreateDTO(String userId, String email, String password, String birth, String gender){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
    }
}

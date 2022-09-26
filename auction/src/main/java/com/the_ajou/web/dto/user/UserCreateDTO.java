package com.the_ajou.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class UserCreateDTO {
    private int userId;
    private String userName;
    private String email;


    @Builder
    public UserCreateDTO(int userId, String userName, String email){
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}

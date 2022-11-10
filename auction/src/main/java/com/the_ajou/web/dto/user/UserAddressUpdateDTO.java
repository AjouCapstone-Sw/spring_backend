package com.the_ajou.web.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserAddressUpdateDTO {
    private int userId;
    private String address;

    @Builder
    UserAddressUpdateDTO(int userId, String address){
        this.userId = userId;
        this.address = address;
    }
}

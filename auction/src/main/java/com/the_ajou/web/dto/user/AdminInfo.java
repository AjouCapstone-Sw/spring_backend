package com.the_ajou.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminInfo {
    private int totalPrice;
    private int commission;
    private int userCount;

    @Builder
    AdminInfo(int totalPrice, int commission, int userCount){
        this.totalPrice = totalPrice;
        this.commission = commission;
        this.userCount = userCount;
    }
}

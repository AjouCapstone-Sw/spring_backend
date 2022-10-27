package com.the_ajou.web.dto.payment;

import com.the_ajou.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentCreateDTO {
    int userId;
    int payment;

    @Builder
    PaymentCreateDTO(int userId, int payment){
        this.userId = userId;
        this.payment = payment;
    }
}

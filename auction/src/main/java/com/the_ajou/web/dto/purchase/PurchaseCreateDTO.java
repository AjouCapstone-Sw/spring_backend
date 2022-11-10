package com.the_ajou.web.dto.purchase;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseCreateDTO {
    int productId;
    int userId;
    String purchaseAddress;

    @Builder
    PurchaseCreateDTO(int productId, int userId, String purchaseAddress){
        this.productId = productId;
        this.userId = userId;
        this.purchaseAddress = purchaseAddress;
    }
}

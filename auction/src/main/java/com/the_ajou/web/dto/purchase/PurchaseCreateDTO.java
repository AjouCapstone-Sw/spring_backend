package com.the_ajou.web.dto.purchase;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseCreateDTO {
    int saleId;
    int userId;
    String purchaseAddress;

    @Builder
    PurchaseCreateDTO(int saleId, int userId, String purchaseAddress){
        this.saleId = saleId;
        this.userId = userId;
        this.purchaseAddress = purchaseAddress;
    }
}

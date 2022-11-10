package com.the_ajou.web.dto.point;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointCreateDTO {
    int userId;
    int productId;
    int purchaseId;
    int pointCharge;

    @Builder
    PointCreateDTO(int userId, int productId, int purchaseId, int pointCharge){
        this.userId = userId;
        this.productId = productId;
        this.purchaseId = purchaseId;
        this.pointCharge = pointCharge;
    }
}

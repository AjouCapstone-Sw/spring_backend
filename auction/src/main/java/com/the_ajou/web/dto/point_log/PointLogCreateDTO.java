package com.the_ajou.web.dto.point_log;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointLogCreateDTO {
    int userId;
    int saleId;
    int purchaseId;
    char type;
    int pointCharge;

    @Builder
    PointLogCreateDTO(int userId, int saleId, int purchaseId, char type, int pointCharge){
        this.userId = userId;
        this.saleId = saleId;
        this.purchaseId = purchaseId;
        this.type = type;
        this.pointCharge = pointCharge;
    }
}

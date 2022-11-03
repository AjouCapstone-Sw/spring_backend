package com.the_ajou.web.dao.pointLog;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PointLogResponseDAO {
    int id;
    int userId;
    int purchaseId;
    int saleId;
    String createdAt;
    char type;
    int pointChange;

    @Builder
    PointLogResponseDAO(int id, int userId, int purchaseId, int saleId, String createdAt, char type, int pointChange){
        this.id = id;
        this.userId = userId;
        this.purchaseId = purchaseId;
        this.saleId = saleId;
        this.createdAt = createdAt;
        this.type = type;
        this.pointChange = pointChange;
    }
}

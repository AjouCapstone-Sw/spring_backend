package com.the_ajou.web.dto.sale;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaleCreateDTO {
    int userId;
    int categoryId;
    String title;
    String content;
    String startAt;
    int startPrice;
    int field;
    int ePrice;

    @Builder
    SaleCreateDTO(int userId, int categoryId, String title, String content, String startAt, int startPrice, int field, int ePrice){
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.startAt = startAt;
        this.startPrice = startPrice;
        this.field = field;
        this.ePrice = ePrice;
    }
}

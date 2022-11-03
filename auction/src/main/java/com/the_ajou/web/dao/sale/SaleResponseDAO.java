package com.the_ajou.web.dao.sale;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SaleResponseDAO {
    int userId;
    int categoryId;
    String title;
    String content;
    String startAt;
    int startPrice;
    int field;
    int endPrice;
    private List<String> images;

    @Builder
    SaleResponseDAO(int userId, int categoryId, String title, String content, String startAt,
                    int startPrice, int field, int endPrice, List<String> images){
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.startAt = startAt;
        this.startPrice = startPrice;
        this.field = field;
        this.endPrice = endPrice;
        this.images = images;
    }
}

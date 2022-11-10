package com.the_ajou.web.dao.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductResponseDAO {
    int userId;
    int categoryId;
    String title;
    String content;
    String startAt;
    int startPrice;
    int instant;
    int endPrice;
    private List<String> images;

    @Builder
    ProductResponseDAO(int userId, int categoryId, String title, String content, String startAt,
                       int startPrice, int instant, int endPrice, List<String> images){
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.startAt = startAt;
        this.startPrice = startPrice;
        this.instant = instant;
        this.endPrice = endPrice;
        this.images = images;
    }
}

package com.the_ajou.web.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductCreateDTO {
    int userId;
    int categoryId;
    String title;
    String content;
    String startAt;
    int startPrice;
    int instant;
    private List<String> images;

    @Builder
    ProductCreateDTO(int userId, int categoryId, String title, String content, String startAt,
                     int startPrice, int instant, List<String> images){
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.startAt = startAt;
        this.startPrice = startPrice;
        this.instant = instant;
        this.images = images;
    }
}

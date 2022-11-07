package com.the_ajou.web.dto.sale;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SaleUpdateDTO {
    int categoryId;
    String title;
    String content;
    String startAt;
    int startPrice;
    int field;
    private List<String> images;

    @Builder
    SaleUpdateDTO(int categoryId, String title, String content, String startAt,
                  int startPrice, int field,  List<String> images){
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.startAt = startAt;
        this.startPrice = startPrice;
        this.field = field;
        this.images = images;
    }
}

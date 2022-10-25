package com.the_ajou.web.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CategoryCreateDTO {
    String categoryName;
    String createdAt;
    String updatedAt;
    char status;

    @Builder
    CategoryCreateDTO(String categoryName, String createdAt, String updatedAt, char status){
        this.categoryName = categoryName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}

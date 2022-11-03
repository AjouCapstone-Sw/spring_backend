package com.the_ajou.web.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CategoryCreateDTO {
    String name;

    @Builder
    CategoryCreateDTO(String name){
        this.name = name;
    }
}

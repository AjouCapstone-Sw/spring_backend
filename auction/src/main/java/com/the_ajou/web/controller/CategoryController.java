package com.the_ajou.web.controller;

import com.the_ajou.service.CategoryService;
import com.the_ajou.web.dto.category.CategoryCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category/create")
    public int createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO){
        return categoryService.createCategory(categoryCreateDTO);
    }
}

package com.the_ajou.web.controller;

import com.the_ajou.service.CategoryService;
import com.the_ajou.web.dto.category.CategoryCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category/create")
    public int createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO){
        return categoryService.createCategory(categoryCreateDTO);
    }

    @PatchMapping("/category/{id}")
    public int deleteCategory(@RequestParam int id){
        return categoryService.deleteCategory(id);
    }
}

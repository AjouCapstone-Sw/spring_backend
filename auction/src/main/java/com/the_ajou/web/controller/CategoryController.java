package com.the_ajou.web.controller;

import com.the_ajou.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category/create")
    public int createCategory(String name){
        return categoryService.createCategory(name);
    }

    @PatchMapping("/category/{id}")
    public int deleteCategory(@RequestParam int id){
        return categoryService.deleteCategory(id);
    }
}

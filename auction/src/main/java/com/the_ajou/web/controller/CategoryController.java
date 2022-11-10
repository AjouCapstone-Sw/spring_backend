package com.the_ajou.web.controller;

import com.the_ajou.service.CategoryService;
import com.the_ajou.web.dao.category.CategoryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category/create")
    public int createCategory(String name){
        return categoryService.createCategory(name);
    }

    @PatchMapping("/category")
    public int deleteCategory(int id){
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/api/v1/category")
    public List<CategoryDAO> getCategories(){
        return categoryService.getCategories();
    }
}

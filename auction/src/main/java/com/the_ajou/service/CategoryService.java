package com.the_ajou.service;

import com.the_ajou.domain.category.Category;
import com.the_ajou.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public int createCategory(String name){
        Category category = Category.builder()
                .name(name)
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status('N')
                .build();
        categoryRepository.save(category);

        return category.getId();
    }

    @Transactional
    public int deleteCategory(int id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        category.setStatus('Y');
        category.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        categoryRepository.save(category);

        return category.getId();
    }
}

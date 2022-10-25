package com.the_ajou.service;

import com.the_ajou.domain.category.Category;
import com.the_ajou.domain.category.CategoryRepository;
import com.the_ajou.web.dto.category.CategoryCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public int createCategory(CategoryCreateDTO categoryCreateDTO){
        Category category = Category.builder()
                .categoryName(categoryCreateDTO.getCategoryName())
                .createdAt(categoryCreateDTO.getCreatedAt())
                .updatedAt(categoryCreateDTO.getUpdatedAt())
                .status(categoryCreateDTO.getStatus())
                .build();
        categoryRepository.save(category);

        return category.getId();
    }
}

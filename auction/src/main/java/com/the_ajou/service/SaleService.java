package com.the_ajou.service;

import com.the_ajou.domain.category.Category;
import com.the_ajou.domain.category.CategoryRepository;
import com.the_ajou.domain.sale.Sale;
import com.the_ajou.domain.sale.SaleRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dto.sale.SaleCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Sale getProduct(int productId){
        Sale findProduct = saleRepository.findById(productId)
                .orElseThrow();
        return findProduct;
    }
    @Transactional
    public List<Sale> getProductList(){
        List<Sale> productList = saleRepository.findAll();
        return productList;
    }

    @Transactional
    public int registerProduct(SaleCreateDTO saleCreateDTO){
        User user = userRepository.findById(saleCreateDTO.getUserId())
                .orElseThrow();
        Category category = categoryRepository.findById(saleCreateDTO.getCategoryId())
                .orElseThrow();

        Sale sale = Sale.builder()
                .user(user)
                .category(category)
                .title(saleCreateDTO.getTitle())
                .content(saleCreateDTO.getContent())
                .startAt(saleCreateDTO.getStartAt())
                .sPrice(saleCreateDTO.getStartPrice())
                .field(saleCreateDTO.getField())
                .ePrice(saleCreateDTO.getEPrice())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status('Y')
                .buyerId(0)
                .build();

        saleRepository.save(sale);


        return sale.getId();
    }
}

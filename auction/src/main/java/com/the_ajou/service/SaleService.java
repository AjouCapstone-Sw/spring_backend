package com.the_ajou.service;

import com.the_ajou.domain.sale.Sale;
import com.the_ajou.domain.sale.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public Sale getProduct(int productId){
        Sale findProduct = saleRepository.findById(productId)
                .orElseThrow();
        return findProduct;
    }

    public List<Sale> getProductList(){
        List<Sale> productList = saleRepository.findAll();
        return productList;
    }
}

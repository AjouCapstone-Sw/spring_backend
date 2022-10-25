package com.the_ajou.web.controller;

import com.the_ajou.domain.sale.Sale;
import com.the_ajou.service.SaleService;
import com.the_ajou.web.dto.sale.SaleCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class SaleController {
    private final SaleService saleService;

    @GetMapping("/product")
    public Sale getProduct(@RequestParam int id){
        return saleService.getProduct(id);
    }

    @GetMapping("/productList")
    public List<Sale> getProductList(){
        return getProductList();
    }

    @PostMapping("/createProduct")
    public int createProduct(@RequestBody SaleCreateDTO saleCreateDTO){
        return saleService.registerProduct(saleCreateDTO);
    }
}

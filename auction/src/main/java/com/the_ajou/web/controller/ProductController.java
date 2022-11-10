package com.the_ajou.web.controller;

import com.the_ajou.service.ProductService;
import com.the_ajou.web.dao.product.ProductResponseDAO;
import com.the_ajou.web.dto.product.ProductCreateDTO;
import com.the_ajou.web.dto.product.ProductUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping("/api/v1/product")
    public ProductResponseDAO getProduct(int productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/productList")
    public List<ProductResponseDAO> getProductList(){
        return productService.getProductList();
    }

    @GetMapping("/productList/category")
    public List<ProductResponseDAO> getProductListByCategoryId(int categoryId){
        return productService.getProductListByCategoryId(categoryId);
    }

    @PostMapping("/product/create")
    public int createProduct(@RequestBody ProductCreateDTO productCreateDTO){
        return productService.createProduct(productCreateDTO);
    }

    @PatchMapping("/product/delete")
    public boolean deleteProduct(int id){
        return productService.deleteProduct(id);
    }

    @PatchMapping("/product/update")
    public int updateProduct(int id, @RequestBody ProductUpdateDTO productsUpdateDTO){
        return productService.updateProduct(id, productsUpdateDTO);
    }
}

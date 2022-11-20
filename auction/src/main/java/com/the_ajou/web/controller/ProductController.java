package com.the_ajou.web.controller;

import com.the_ajou.service.ProductService;
import com.the_ajou.web.dao.product.ProductResponseDAO;
import com.the_ajou.web.dao.product.ProductSearchResponseDAO;
import com.the_ajou.web.dto.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product/{productId}")
    public ProductResponseDAO getProduct(@PathVariable("productId") int productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/product/list")
    public List<ProductSearchResponseDAO> getProductList(){
        return productService.getProductList();
    }

    @GetMapping("/productList/{categoryId}")
    public List<ProductSearchResponseDAO> getProductListByCategoryId(@PathVariable("categoryId") int categoryId){
        return productService.getProductListByCategoryId(categoryId);
    }

    @PostMapping("/product/create")
    public int createProduct(@RequestBody ProductCreateDTO productCreateDTO){
        return productService.createProduct(productCreateDTO);
    }

    @PatchMapping("/product/delete/{productId}")
    public boolean deleteProduct(@PathVariable("productId") int productId){
        return productService.deleteProduct(productId);
    }

    @PatchMapping("/product/update")
    public boolean updateProduct(@RequestBody ProductUpdateDTO productsUpdateDTO){
        return productService.updateProduct(productsUpdateDTO);
    }

    @GetMapping("/product/search/{keyword}")
    public List<ProductSearchResponseDAO> getProductSearchList(@PathVariable("keyword") String keyword) throws ParseException {
        return productService.getProductBySearch(keyword);
    }

    @PostMapping("/product/instantPurchase")
    public boolean instantPurchase(@RequestBody ProductInstantPurchaseDTO productInstantPurchaseDTO){
        return productService.instantPurchase(productInstantPurchaseDTO);
    }

    @PostMapping("/product/auctionPurchase")
    public boolean auctionPurchase(@RequestBody ProductAuctionPurchaseDTO productAuctionPurchaseDTO){
        return productService.auctionPurchase(productAuctionPurchaseDTO);
    }

}

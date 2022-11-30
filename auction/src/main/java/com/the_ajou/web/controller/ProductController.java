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

    @GetMapping("/api/product/{productId}")
    public ProductResponseDAO getProduct(@PathVariable("productId") int productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/api/product/list")
    public List<ProductSearchResponseDAO> getProductList(){
        return productService.getProductList();
    }

    @GetMapping("/api/productList/{categoryId}")
    public List<ProductSearchResponseDAO> getProductListByCategoryId(@PathVariable("categoryId") int categoryId){
        return productService.getProductListByCategoryId(categoryId);
    }

    @GetMapping("/api/productList/like/{userId}")
    public List<ProductSearchResponseDAO> getProductListByLike(@PathVariable("userId") int userId){
        return productService.getProductListByLike(userId);
    }

    @PostMapping("/api/product/create")
    public int createProduct(@RequestBody ProductCreateDTO productCreateDTO){
        return productService.createProduct(productCreateDTO);
    }

    @PatchMapping("/api/product/delete/{productId}")
    public boolean deleteProduct(@PathVariable("productId") int productId){
        return productService.deleteProduct(productId);
    }

    @PatchMapping("/api/product/update")
    public boolean updateProduct(@RequestBody ProductUpdateDTO productsUpdateDTO){
        return productService.updateProduct(productsUpdateDTO);
    }

    @GetMapping("/api/product/search/{keyword}")
    public List<ProductSearchResponseDAO> getProductSearchList(@PathVariable("keyword") String keyword) throws ParseException {
        return productService.getProductBySearch(keyword);
    }

    @PostMapping("/api/product/instantPurchase")
    public boolean instantPurchase(@RequestBody ProductInstantPurchaseDTO productInstantPurchaseDTO){
        return productService.instantPurchase(productInstantPurchaseDTO);
    }

    @PostMapping("/api/product/auctionPurchase")
    public boolean auctionPurchase(@RequestBody ProductAuctionPurchaseDTO productAuctionPurchaseDTO){
        return productService.auctionPurchase(productAuctionPurchaseDTO);
    }

    @PatchMapping("/api/product/auctionFail/{productId}")
    public boolean auctionFail(@PathVariable("productId") int productId){
        return productService.auctionFail(productId);
    }

    @PatchMapping("api/product/auction/sellerIn")
    public boolean sellerIn(int productId){
        return productService.sellerIn(productId);
    }
}

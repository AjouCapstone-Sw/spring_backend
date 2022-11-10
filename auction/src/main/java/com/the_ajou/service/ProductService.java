package com.the_ajou.service;

import com.the_ajou.domain.category.Category;
import com.the_ajou.domain.category.CategoryRepository;
import com.the_ajou.domain.product.Product;
import com.the_ajou.domain.product.ProductRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dao.product.ProductResponseDAO;
import com.the_ajou.web.dto.product.ProductCreateDTO;
import com.the_ajou.web.dto.product.ProductUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ProductResponseDAO getProduct(int id){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 상품입니다."));

        List<String> images = new LinkedList<>();

        if(!product.getProductImage1().isBlank())
            images.add(product.getProductImage1());
        if(!product.getProductImage2().isBlank())
            images.add(product.getProductImage2());
        if(!product.getProductImage3().isBlank())
            images.add(product.getProductImage3());
        if(!product.getProductImage4().isBlank())
            images.add(product.getProductImage4());
        if(!product.getProductImage5().isBlank())
            images.add(product.getProductImage5());

        return ProductResponseDAO.builder()
                .userId(product.getUser().getId())
                .categoryId(product.getCategory().getId())
                .title(product.getTitle())
                .content(product.getContent())
                .startAt(product.getStartAt())
                .startPrice(product.getStartPrice())
                .instant(product.getInstant())
                .endPrice(product.getEndPrice())
                .images(images)
                .build();
    }

    @Transactional
    public List<ProductResponseDAO> getProductList(){
        List<Product> products = productRepository.findAll();
        List<ProductResponseDAO> productResponseDAOS = new LinkedList<>();

        for(Product product : products){
            if(product.getStatus() == 'Y')
                continue;
            List<String> images = new LinkedList<>();

            if(!product.getProductImage1().isBlank())
                images.add(product.getProductImage1());
            if(!product.getProductImage2().isBlank())
                images.add(product.getProductImage2());
            if(!product.getProductImage3().isBlank())
                images.add(product.getProductImage3());
            if(!product.getProductImage4().isBlank())
                images.add(product.getProductImage4());
            if(!product.getProductImage5().isBlank())
                images.add(product.getProductImage5());

            ProductResponseDAO productResponseDAO = ProductResponseDAO.builder()
                    .userId(product.getUser().getId())
                    .categoryId(product.getCategory().getId())
                    .title(product.getTitle())
                    .content(product.getContent())
                    .startAt(product.getStartAt())
                    .startPrice(product.getStartPrice())
                    .instant(product.getInstant())
                    .endPrice(product.getEndPrice())
                    .images(images)
                    .build();
            productResponseDAOS.add(productResponseDAO);
        }

        Collections.reverse(productResponseDAOS);

        return productResponseDAOS;
    }

    @Transactional
    public List<ProductResponseDAO> getProductListByCategoryId(int categoryId){
        List<Product> products = productRepository.findAll();
        List<ProductResponseDAO> productResponseDAOS = new LinkedList<>();

        for(Product product : products){
            if(product.getStatus() == 'Y')
                continue;
            if(product.getCategory().getId() != categoryId)
                continue;

            List<String> images = new LinkedList<>();

            if(!product.getProductImage1().isBlank())
                images.add(product.getProductImage1());
            if(!product.getProductImage2().isBlank())
                images.add(product.getProductImage2());
            if(!product.getProductImage3().isBlank())
                images.add(product.getProductImage3());
            if(!product.getProductImage4().isBlank())
                images.add(product.getProductImage4());
            if(!product.getProductImage5().isBlank())
                images.add(product.getProductImage5());

            ProductResponseDAO productResponseDAO = ProductResponseDAO.builder()
                    .userId(product.getUser().getId())
                    .categoryId(product.getCategory().getId())
                    .title(product.getTitle())
                    .content(product.getContent())
                    .startAt(product.getStartAt())
                    .startPrice(product.getStartPrice())
                    .instant(product.getInstant())
                    .endPrice(product.getEndPrice())
                    .images(images)
                    .build();
            productResponseDAOS.add(productResponseDAO);
        }

        Collections.reverse(productResponseDAOS);

        return productResponseDAOS;
    }

    @Transactional
    public int createProduct(ProductCreateDTO productCreateDTO){
        User user = userRepository.findById(productCreateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Category category = categoryRepository.findById(productCreateDTO.getCategoryId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        User buyer = new User();

        int imagesLength = productCreateDTO.getImages().size();

        Product product = Product.builder()
                .user(user)
                .category(category)
                .title(productCreateDTO.getTitle())
                .content(productCreateDTO.getContent())
                .startAt(productCreateDTO.getStartAt())
                .startPrice(productCreateDTO.getStartPrice())
                .instant(productCreateDTO.getInstant())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status('N')
                .buyerId(0)
                .productImage1(0 < imagesLength ?  productCreateDTO.getImages().get(0) : "")
                .productImage2(1 < imagesLength ? productCreateDTO.getImages().get(1) : "")
                .productImage3(2 < imagesLength ? productCreateDTO.getImages().get(2) : "")
                .productImage4(3 < imagesLength ? productCreateDTO.getImages().get(3) : "")
                .productImage5(4 < imagesLength ? productCreateDTO.getImages().get(4) : "")
                .build();

        productRepository.save(product);


        return product.getId();
    }

    @Transactional
    public boolean deleteProduct(int productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 상품입니다."));

        product.setStatus('Y');
        product.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return product.getStatus() == 'Y';
    }

    @Transactional
    public int updateProduct(int productId, ProductUpdateDTO productUpdateDTO){
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 상품입니다."));
        Category category = categoryRepository.findById(productUpdateDTO.getCategoryId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        int imagesLength = productUpdateDTO.getImages().size();

        product.setCategory(category);
        product.setTitle(productUpdateDTO.getTitle());
        product.setContent(productUpdateDTO.getContent());
        product.setStartAt(productUpdateDTO.getStartAt());
        product.setStartPrice(productUpdateDTO.getStartPrice());
        product.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        product.setInstant(productUpdateDTO.getInstant());
        product.setProductImage1(0 < imagesLength ?  productUpdateDTO.getImages().get(0) : "");
        product.setProductImage2(1 < imagesLength ?  productUpdateDTO.getImages().get(1) : "");
        product.setProductImage3(2 < imagesLength ?  productUpdateDTO.getImages().get(2) : "");
        product.setProductImage4(3 < imagesLength ?  productUpdateDTO.getImages().get(3) : "");
        product.setProductImage5(4 < imagesLength ?  productUpdateDTO.getImages().get(4) : "");

        return 1;
    }
}

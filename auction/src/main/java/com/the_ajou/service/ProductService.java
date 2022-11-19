package com.the_ajou.service;

import com.the_ajou.domain.category.Category;
import com.the_ajou.domain.category.CategoryRepository;
import com.the_ajou.domain.interests.InterestRepository;
import com.the_ajou.domain.product.Product;
import com.the_ajou.domain.product.ProductRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dao.product.ProductResponseDAO;
import com.the_ajou.web.dao.product.ProductSearchResponseDAO;
import com.the_ajou.web.dto.product.ProductCreateDTO;
import com.the_ajou.web.dto.product.ProductUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final InterestRepository interestRepository;

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

        Date startTime;
        Date endTime;
        Date nowTime;

        boolean before = false;
        boolean after = false;
        String endTimeStr = "";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        try {
            endTime = simpleDateFormat.parse(product.getStartTime());
            calendar.setTime(endTime);
            calendar.add(Calendar.MINUTE, product.getDuration());

            endTimeStr = simpleDateFormat.format(calendar.getTime());

            startTime = simpleDateFormat.parse(product.getStartTime());
            endTime = simpleDateFormat.parse(endTimeStr);
            nowTime = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            before = nowTime.after(startTime);
            after = nowTime.before(endTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ProductResponseDAO.builder()
                .productId(product.getId())
                .seller(product.getUser().getNickname())
                .title(product.getTitle())
                .description(product.getDescription())
                .startTime(product.getStartTime())
                .endTime(endTimeStr)
                .startPrice(product.getStartPrice())
                .instant(product.getInstant())
                .buyNowPrice(product.getBuyNowPrice())
                .duration(product.getDuration())
                .bidPrice(product.getBidPrice())
                .like(interestRepository.findByProductIdAndUserId(product.getId(), product.getUser().getId()) != null)
                .live(before && after)
                .productImages(images)
                .build();
    }

    @Transactional
    public List<ProductSearchResponseDAO> getProductList(){
        List<Product> products = productRepository.findAll();
        List<ProductSearchResponseDAO> productSearchResponseDAOS = new LinkedList<>();

        for(Product product : products){
            if(product.getStatus() == 'N'){
                Date startTime;
                Date endTime;
                Date nowTime;

                boolean before = false;
                boolean after = false;
                String endTimeStr = "";
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat  = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
                try {
                    endTime = simpleDateFormat.parse(product.getStartTime());
                    calendar.setTime(endTime);
                    calendar.add(Calendar.MINUTE, product.getDuration());

                    endTimeStr = simpleDateFormat.format(calendar.getTime());

                    startTime = simpleDateFormat.parse(product.getStartTime());
                    endTime = simpleDateFormat.parse(endTimeStr);
                    nowTime = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                    before = nowTime.after(startTime);
                    after = nowTime.before(endTime);

                } catch (ParseException e) {
                    e.printStackTrace();
                }


                ProductSearchResponseDAO productSearchResponseDAO = ProductSearchResponseDAO.builder()
                        .productId(product.getId())
                        .title(product.getTitle())
                        .buyNowPrice(product.getBuyNowPrice())
                        .live(before && after)
                        .like(interestRepository.findByProductIdAndUserId(product.getId(), product.getUser().getId()) != null)
                        .image(product.getProductImage1())
                        .build();
                productSearchResponseDAOS.add(productSearchResponseDAO);
            }
        }

        Collections.reverse(productSearchResponseDAOS);

        return productSearchResponseDAOS;
    }

    @Transactional
    public List<ProductSearchResponseDAO> getProductListByCategoryId(int categoryId){
        List<Product> products;
        List<ProductSearchResponseDAO> productSearchResponseDAOS = new LinkedList<>();

        if(categoryId == 1)
            products = productRepository.findAll();
        else
            products = productRepository.findAllByCategoryId(categoryId);

        for(Product product : products){
            if(product.getStatus() == 'N'){
                Date startTime;
                Date endTime;
                Date nowTime;

                boolean before = false;
                boolean after = false;
                String endTimeStr = "";
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat  = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
                try {
                    endTime = simpleDateFormat.parse(product.getStartTime());
                    calendar.setTime(endTime);
                    calendar.add(Calendar.MINUTE, product.getDuration());

                    endTimeStr = simpleDateFormat.format(calendar.getTime());

                    startTime = simpleDateFormat.parse(product.getStartTime());
                    endTime = simpleDateFormat.parse(endTimeStr);
                    nowTime = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                    before = nowTime.after(startTime);
                    after = nowTime.before(endTime);

                } catch (ParseException e) {
                    e.printStackTrace();
                }



                ProductSearchResponseDAO productSearchResponseDAO = ProductSearchResponseDAO.builder()
                        .productId(product.getId())
                        .title(product.getTitle())
                        .buyNowPrice(product.getBuyNowPrice())
                        .live(before && after)
                        .like(interestRepository.findByProductIdAndUserId(product.getId(), product.getUser().getId()) != null)
                        .image(product.getProductImage1())
                        .build();
                productSearchResponseDAOS.add(productSearchResponseDAO);
            }
        }

        Collections.reverse(productSearchResponseDAOS);

        return productSearchResponseDAOS;
    }

    @Transactional
    public int createProduct(ProductCreateDTO productCreateDTO){
        User user = userRepository.findById(productCreateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Category category = categoryRepository.findById(productCreateDTO.getCategoryId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        int imagesLength = productCreateDTO.getProductImages().size();

        Product product = Product.builder()
                .user(user)
                .category(category)
                .title(productCreateDTO.getTitle())
                .description(productCreateDTO.getDescription())
                .startTime(productCreateDTO.getStartTime())
                .duration(productCreateDTO.getDuration())
                .bidPrice(productCreateDTO.getBidPrice())
                .startPrice(productCreateDTO.getStartPrice())
                .instant(productCreateDTO.getInstant())
                .buyNowPrice(productCreateDTO.getBuyNowPrice())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endPrice(productCreateDTO.getInstant() == 1 ? productCreateDTO.getStartPrice() : 0)
                .status('N')
                .buyerId(0)
                .productImage1(0 < imagesLength ?  productCreateDTO.getProductImages().get(0) : "")
                .productImage2(1 < imagesLength ? productCreateDTO.getProductImages().get(1) : "")
                .productImage3(2 < imagesLength ? productCreateDTO.getProductImages().get(2) : "")
                .productImage4(3 < imagesLength ? productCreateDTO.getProductImages().get(3) : "")
                .productImage5(4 < imagesLength ? productCreateDTO.getProductImages().get(4) : "")
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
    public boolean updateProduct(ProductUpdateDTO productUpdateDTO){
        Product product = productRepository.findById(productUpdateDTO.getProductId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 상품입니다."));
        Category category = categoryRepository.findById(productUpdateDTO.getCategoryId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        int imagesLength = productUpdateDTO.getImages().size();

        product.setCategory(category);
        product.setTitle(productUpdateDTO.getTitle());
        product.setDescription(productUpdateDTO.getDescription());
        product.setStartTime(productUpdateDTO.getStartTime());
        product.setStartPrice(productUpdateDTO.getStartPrice());
        product.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        product.setInstant(productUpdateDTO.getInstant());
        product.setDuration(productUpdateDTO.getDuration());
        product.setBidPrice(productUpdateDTO.getBidPrice());
        product.setEndPrice(productUpdateDTO.getInstant() == 1 ? productUpdateDTO.getStartPrice() : 0);
        product.setProductImage1(0 < imagesLength ?  productUpdateDTO.getImages().get(0) : "");
        product.setProductImage2(1 < imagesLength ?  productUpdateDTO.getImages().get(1) : "");
        product.setProductImage3(2 < imagesLength ?  productUpdateDTO.getImages().get(2) : "");
        product.setProductImage4(3 < imagesLength ?  productUpdateDTO.getImages().get(3) : "");
        product.setProductImage5(4 < imagesLength ?  productUpdateDTO.getImages().get(4) : "");

        return product.getId() == productUpdateDTO.getProductId();
    }

    @Transactional
    public List<ProductSearchResponseDAO> getProductBySearch(String keyword) throws ParseException {
        List<Product> products = productRepository.findAll();
        List<ProductSearchResponseDAO> productSearchResponseDAOS = new LinkedList<>();

        for(Product product : products){
            if(product.getCategory().getName().contains(keyword) || product.getTitle().contains(keyword)){

                Date startTime;
                Date current;
                SimpleDateFormat simpleDateFormat  = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startTime = f.parse(product.getStartTime());
                current = f.parse(simpleDateFormat.format(new Date()));
                long timeDiff = (current.getTime() - startTime.getTime()) / 60000;



                ProductSearchResponseDAO productSearchResponseDAO = ProductSearchResponseDAO.builder()
                        .productId(product.getId())
                        .title(product.getTitle())
                        .buyNowPrice(product.getBuyNowPrice())
                        .live(0<= timeDiff && timeDiff <= product.getDuration())
                        .like(interestRepository.findByProductIdAndUserId(product.getId(), product.getUser().getId()) != null)
                        .image(product.getProductImage1())
                        .build();
                productSearchResponseDAOS.add(productSearchResponseDAO);
            }
        }

        Collections.reverse(productSearchResponseDAOS);

        return productSearchResponseDAOS;
    }

}

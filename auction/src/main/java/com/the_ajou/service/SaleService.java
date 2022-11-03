package com.the_ajou.service;

import com.the_ajou.domain.category.Category;
import com.the_ajou.domain.category.CategoryRepository;
import com.the_ajou.domain.sale.Sale;
import com.the_ajou.domain.sale.SaleRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dao.sale.SaleResponseDAO;
import com.the_ajou.web.dto.sale.SaleCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public SaleResponseDAO getSale(int id){
        Sale sale = saleRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 상품입니다."));

        List<String> images = new LinkedList<>();

        if(!sale.getSaleImage1().isBlank())
            images.add(sale.getSaleImage1());
        if(!sale.getSaleImage2().isBlank())
            images.add(sale.getSaleImage2());
        if(!sale.getSaleImage3().isBlank())
            images.add(sale.getSaleImage3());
        if(!sale.getSaleImage4().isBlank())
            images.add(sale.getSaleImage4());
        if(!sale.getSaleImage5().isBlank())
            images.add(sale.getSaleImage5());

        return SaleResponseDAO.builder()
                .userId(sale.getUser().getId())
                .categoryId(sale.getCategory().getId())
                .title(sale.getTitle())
                .content(sale.getContent())
                .startAt(sale.getStartAt())
                .startPrice(sale.getStartPrice())
                .field(sale.getField())
                .endPrice(sale.getEndPrice())
                .images(images)
                .build();
    }

    @Transactional
    public List<SaleResponseDAO> getSaleList(){
        List<Sale> sales = saleRepository.findAll();
        List<SaleResponseDAO> saleResponseDAOS = new LinkedList<>();

        for(Sale sale : sales){
            List<String> images = new LinkedList<>();

            if(!sale.getSaleImage1().isBlank())
                images.add(sale.getSaleImage1());
            if(!sale.getSaleImage2().isBlank())
                images.add(sale.getSaleImage2());
            if(!sale.getSaleImage3().isBlank())
                images.add(sale.getSaleImage3());
            if(!sale.getSaleImage4().isBlank())
                images.add(sale.getSaleImage4());
            if(!sale.getSaleImage5().isBlank())
                images.add(sale.getSaleImage5());

            SaleResponseDAO saleResponseDAO = SaleResponseDAO.builder()
                    .userId(sale.getUser().getId())
                    .categoryId(sale.getCategory().getId())
                    .title(sale.getTitle())
                    .content(sale.getContent())
                    .startAt(sale.getStartAt())
                    .startPrice(sale.getStartPrice())
                    .field(sale.getField())
                    .endPrice(sale.getEndPrice())
                    .images(images)
                    .build();
            saleResponseDAOS.add(saleResponseDAO);
        }
        return saleResponseDAOS;
    }

    @Transactional
    public int createSale(SaleCreateDTO saleCreateDTO){
        User user = userRepository.findById(saleCreateDTO.getUserId())
                .orElseThrow();
        Category category = categoryRepository.findById(saleCreateDTO.getCategoryId())
                .orElseThrow();

        int imagesLength = saleCreateDTO.getImages().size();

        Sale sale = Sale.builder()
                .user(user)
                .category(category)
                .title(saleCreateDTO.getTitle())
                .content(saleCreateDTO.getContent())
                .startAt(saleCreateDTO.getStartAt())
                .startPrice(saleCreateDTO.getStartPrice())
                .field(saleCreateDTO.getField())
                .endPrice(saleCreateDTO.getEndPrice())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status('Y')
                .buyerId(0)
                .saleImage1(0 < imagesLength ?  saleCreateDTO.getImages().get(0) : "")
                .saleImage2(1 < imagesLength ? saleCreateDTO.getImages().get(1) : "")
                .saleImage3(2 < imagesLength ? saleCreateDTO.getImages().get(2) : "")
                .saleImage4(3 < imagesLength ? saleCreateDTO.getImages().get(3) : "")
                .saleImage5(4 < imagesLength ? saleCreateDTO.getImages().get(4) : "")
                .build();

        saleRepository.save(sale);


        return sale.getId();
    }

    @Transactional
    public int deleteSale(int productId){
        Sale sale = saleRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 상품입니다."));

        sale.setStatus('Y');
        sale.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        saleRepository.save(sale);

        return sale.getId();
    }
}

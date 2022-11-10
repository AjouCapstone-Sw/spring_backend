package com.the_ajou.service;

import com.the_ajou.domain.purchase.Purchase;
import com.the_ajou.domain.purchase.PurchaseRepository;
import com.the_ajou.domain.product.Product;
import com.the_ajou.domain.product.ProductRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dao.purchase.PurchaseResponseDAO;
import com.the_ajou.web.dto.purchase.PurchaseCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;

    @Transactional
    public int createPurchaseHistory(PurchaseCreateDTO purchaseCreateDTO){
        Product product = productRepository.findById(purchaseCreateDTO.getProductId())
                .orElseThrow(()->new IllegalArgumentException("상품이 존재하지 않습니다."));
        User user = userRepository.findById(purchaseCreateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        Purchase purchase = Purchase.builder()
                .product(product)
                .user(user)
                .purchaseAddress(purchaseCreateDTO.getPurchaseAddress())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status('N')
                .build();

        purchaseRepository.save(purchase);

        return purchase.getId();
    }


    @Transactional
    public List<PurchaseResponseDAO> getPurchasesByUserId(int userId){
        List<Purchase> purchases = purchaseRepository.getPurchasesByUserId(userId);
        List<PurchaseResponseDAO> purchaseResponseDAOs = new LinkedList<>();

        for(Purchase purchase : purchases){
            if(purchase.getStatus() == 'Y')
                continue;
            PurchaseResponseDAO purchaseResponseDAO = PurchaseResponseDAO.builder()
                    .productId(purchase.getProduct().getId())
                    .userId(purchase.getUser().getId())
                    .purchaseAddress(purchase.getPurchaseAddress())
                    .createAt(purchase.getCreatedAt())
                    .updateAt(purchase.getUpdatedAt())
                    .status(purchase.getStatus())
                    .build();
            purchaseResponseDAOs.add(purchaseResponseDAO);
        }

        return purchaseResponseDAOs;
    }

    @Transactional
    public PurchaseResponseDAO getPurchase(int purchaseId){
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 구매내역입니다."));

        return PurchaseResponseDAO.builder()
                .productId(purchase.getProduct().getId())
                .userId(purchase.getUser().getId())
                .purchaseAddress(purchase.getPurchaseAddress())
                .createAt(purchase.getCreatedAt())
                .updateAt(purchase.getUpdatedAt())
                .status(purchase.getStatus())
                .build();
    }

    @Transactional
    public int deletePurchase(int purchaseId){
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 구매내역입니다."));

        purchase.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        purchase.setStatus('Y');

        return purchase.getId();
    }
}

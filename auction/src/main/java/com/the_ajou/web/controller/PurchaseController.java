package com.the_ajou.web.controller;

import com.the_ajou.service.PurchaseService;
import com.the_ajou.web.dao.purchase.PurchaseResponseDAO;
import com.the_ajou.web.dto.purchase.PurchaseCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/purchase")
    private int createPurchaseLog(@RequestBody PurchaseCreateDTO purchaseCreateDTO){
        return purchaseService.createPurchaseHistory(purchaseCreateDTO);
    }

    @GetMapping("/purchase/list")
    private List<PurchaseResponseDAO> getPurchasesByUserId(int userId){
        return purchaseService.getPurchasesByUserId(userId);
    }

    @GetMapping("/purchase")
    private PurchaseResponseDAO getPurchaseById(int purchaseId){
        return purchaseService.getPurchase(purchaseId);
    }

    @DeleteMapping("/purchase/delete")
    private void updatePurchase(int id){
        purchaseService.deletePurchase(id);
    }

}

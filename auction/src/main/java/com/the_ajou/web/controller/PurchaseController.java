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
    int createPurchaseLog(@RequestBody PurchaseCreateDTO purchaseCreateDTO){
        return purchaseService.createPurchaseHistory(purchaseCreateDTO);
    }

    @GetMapping("/purchase/list")
    List<PurchaseResponseDAO> getPurchasesByUserId(int userId){
        return purchaseService.getPurchasesByUserId(userId);
    }

    @GetMapping("/purchase")
    PurchaseResponseDAO getPurchaseById(int purchaseId){
        return purchaseService.getPurchase(purchaseId);
    }

    @PatchMapping("/purchase/delete")
    int updatePurchase(int id){
        return purchaseService.deletePurchase(id);
    }

}

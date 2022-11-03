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

    @GetMapping("/purchase/{userId}")
    List<PurchaseResponseDAO> getPurchasesByUserId(@RequestParam int userId){
        return purchaseService.getPurchasesByUserId(userId);
    }

    @GetMapping("/purchase/{id}")
    PurchaseResponseDAO getPurchaseById(@RequestParam int purchaseId){
        return purchaseService.getPurchase(purchaseId);
    }

    @PatchMapping("/purchase/delete/{id}")
    int updatePurchase(@RequestParam int id){
        return purchaseService.deletePurchase(id);
    }

}

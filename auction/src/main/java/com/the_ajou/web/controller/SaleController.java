package com.the_ajou.web.controller;

import com.the_ajou.service.SaleService;
import com.the_ajou.web.dao.sale.SaleResponseDAO;
import com.the_ajou.web.dto.sale.SaleCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SaleController {
    private final SaleService saleService;

    @GetMapping("/sale")
    public SaleResponseDAO getSale(@RequestParam int id){
        return saleService.getSale(id);
    }

    @GetMapping("/saleList")
    public List<SaleResponseDAO> getSaleList(){
        return saleService.getSaleList();
    }

    @PostMapping("/sale/create")
    public int createSale(@RequestBody SaleCreateDTO saleCreateDTO){
        return saleService.createSale(saleCreateDTO);
    }

    @PatchMapping("/sale/delete/{id}")
    public int deleteSale(int id){
        return saleService.deleteSale(id);
    }
}

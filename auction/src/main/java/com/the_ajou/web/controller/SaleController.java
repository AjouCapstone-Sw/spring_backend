package com.the_ajou.web.controller;

import com.the_ajou.domain.sale.Sale;
import com.the_ajou.service.SaleService;
import com.the_ajou.web.dao.sale.SaleResponseDAO;
import com.the_ajou.web.dto.sale.SaleCreateDTO;
import com.the_ajou.web.dto.sale.SaleUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SaleController {
    private final SaleService saleService;

    @GetMapping("/sale")
    public SaleResponseDAO getSale(int id){
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

    @PatchMapping("/sale/delete")
    public int deleteSale(int id){
        return saleService.deleteSale(id);
    }

    @PatchMapping("/sale/update")
    public int updateSale(int id, @RequestBody SaleUpdateDTO salesUpdateDTO){
        return saleService.updateSale(id, salesUpdateDTO);
    }
}

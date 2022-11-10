package com.the_ajou.web.dto.invoice;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InvoiceCreateDTO {
    private int productId;
    private String shippingCompany;
    private int invoice;

    @Builder
    InvoiceCreateDTO(int productId, String shippingCompany, int invoice){
        this.productId = productId;
        this.shippingCompany = shippingCompany;
        this.invoice = invoice;
    }
}

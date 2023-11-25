package com.example.test.dto;

import com.example.test.entity.Product;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Records implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    @NotBlank(message = "entry_date is required")
    private String entry_date;

    @NotBlank(message = "itemcode is required")
    private String itemcode;

    @NotBlank(message = "itemname is required")
    private String itemname;

    @NotBlank(message = "itemquantity is required")
    private String itemquantity;

    @NotBlank(message = "upc is required")
    private String upc;

    @NotBlank(message = "ean is required")
    private String ean;

    @NotBlank(message = "sku is required")
    private String sku;

    @NotBlank(message = "isbn is required")
    private String isbn;

    @NotBlank(message = "mpc is required")
    private String mpc;

    @NotBlank(message = "sStatus is required")
    private String sStatus;

    public static Records from(Product product) {
        return Records.builder()
                .id(product.getId())
                .entry_date(product.getEntry_date())
                .itemcode(product.getItemcode())
                .itemname(product.getItemname())
                .itemquantity(product.getItemquantity())
                .upc(product.getUpc())
                .ean(product.getEan())
                .sku(product.getSku())
                .isbn(product.getIsbn())
                .mpc(product.getMpc())
                .sStatus(product.getSStatus())
                .build();
    }
}

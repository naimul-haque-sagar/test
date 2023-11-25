package com.example.test.entity;

import com.example.test.dto.Records;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entry_date;

    private String itemcode;

    private String itemname;

    private String itemquantity;

    private String upc;

    private String ean;

    private String sku;

    private String isbn;

    private String mpc;

    private String sStatus;

    public static Product from(Records records) {
        return Product.builder()
                .entry_date(records.getEntry_date())
                .itemcode(records.getItemcode())
                .itemname(records.getItemname())
                .itemquantity(records.getItemquantity())
                .upc(records.getUpc())
                .ean(records.getEan())
                .sku(records.getSku())
                .isbn(records.getIsbn())
                .mpc(records.getMpc())
                .sStatus(records.getSStatus())
                .build();
    }
}

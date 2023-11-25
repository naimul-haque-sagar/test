package com.example.test.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank
    private String table;

    @NotEmpty(message = "Products could not be empty")
    private List<Records> records;
}

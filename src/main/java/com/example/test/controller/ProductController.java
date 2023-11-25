package com.example.test.controller;

import com.example.test.dto.ProductDto;
import com.example.test.dto.Records;
import com.example.test.entity.Product;
import com.example.test.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        List<Records> records = productService.addProduct(productDto);

        return ResponseEntity.ok(records);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Product> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList.stream().map(Records::from).toList());
    }

}

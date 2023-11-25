package com.example.test.service;

import com.example.test.dto.ProductDto;
import com.example.test.dto.Records;
import com.example.test.entity.Product;
import com.example.test.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Records> addProduct(ProductDto productDto) {
        List<Product> products = productDto.getRecords()
                .stream()
                .map(Product::from)
                .collect(Collectors.toList());

        List<Product> productList = productRepository.saveAll(products);

        return productList
                .stream()
                .map(Records::from)
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

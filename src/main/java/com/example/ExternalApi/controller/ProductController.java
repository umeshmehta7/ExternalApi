package com.example.ExternalApi.controller;

import com.example.ExternalApi.dto.ProductDto;
import com.example.ExternalApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{cusine}")
    public List<ProductDto> getProductsByCuisine(@PathVariable String cusine) {
        return productService.getProductsByCuisine(cusine);
    }
}

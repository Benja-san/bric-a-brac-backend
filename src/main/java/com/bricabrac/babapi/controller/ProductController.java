package com.bricabrac.babapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bricabrac.babapi.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.bricabrac.babapi.entity.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Operation(summary = "Get all products", description = "Get all products")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/")
    public List<Product> getAllProducts(@RequestParam(value = "onsales", required = false) Boolean onSales) {
        if (onSales != null) {
            return productRepository.findAllByOnSales(onSales);
        }
        return productRepository.findAll();
    }

    @Operation(summary = "get product", description = "Get a product")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{slug}")
    public Product getProductBySlug(@PathVariable String slug) {
        return productRepository.findBySlug(slug).orElseThrow();
    }

    @Operation(summary = "Add product", description = "Add a product")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/add")
    public Product addProduct(String name, double price, String description) {
        Product product = new Product(name, price, description);
        return productRepository.save(product);
    }

    @Operation(summary = "Update product", description = "Update a product")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, String name, double price, String description) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        return productRepository.save(product);
    }

    @Operation(summary = "Delete product", description = "Delete a product")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

}

package com.bricabrac.babapi.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bricabrac.babapi.entity.Product;
import com.github.javafaker.Faker;

@Component
//CommandLineRunner allows us to run code at application startup
public class ProductGeneratorRepository implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final Faker faker = new Faker();
    Product product;

    public ProductGeneratorRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //When implementing CommandLineRunner, we need to override the run method
    @Override
    public void run(String... args) throws Exception {
        if(productRepository.count() > 0) {
            return;
        }
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String name = faker.commerce().productName();
            product = new Product(
                name, 
                name.toLowerCase().replace(" ", "-"),
                faker.number().randomDouble(2, 1, 100), 
                faker.lorem().paragraph(),
                faker.bool().bool()
            );
            products.add(product);
        }
        productRepository.saveAll(products);
    }

}

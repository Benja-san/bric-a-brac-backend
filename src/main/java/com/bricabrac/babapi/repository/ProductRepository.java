package com.bricabrac.babapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bricabrac.babapi.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    public Optional<Product> findBySlug(String slug);
}

package com.bricabrac.babapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slug;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private boolean onSales;

    public Product() {
    }

    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product(String name, String slug, double price, String description, boolean onSales) {
        this.name = name;
        this.slug = slug;
        this.price = price;
        this.description = description;
        this.onSales = onSales;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOnSales() {
        return onSales;
    }

    public void setOnSales(boolean onSales) {
        this.onSales = onSales;
    }
    
}

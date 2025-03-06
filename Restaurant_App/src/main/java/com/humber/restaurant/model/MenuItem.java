package com.humber.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MenuItem")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;
    
    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Default constructor (required by Hibernate)
    
    public MenuItem() {}

    // Constructor with parameters
    public MenuItem(String name, String description, double price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter for category
    public Category getCategory() {
        return category;
    }

    // Setter for category
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MenuItem{id=" + id + 
               ", name='" + name + '\'' + 
               ", description='" + description + '\'' + 
               ", price=" + price + 
               ", category=" + (category != null ? category.getName() : "null") + 
               '}';
    }
}

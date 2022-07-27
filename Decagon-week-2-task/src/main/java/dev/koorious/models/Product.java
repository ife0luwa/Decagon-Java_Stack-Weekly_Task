package dev.koorious.models;

import java.util.Objects;

public class Product {
    private String category;
    private String name;
    private int quantity;
    private Double price;


    public Product() {

    }

    public Product(String category, String name, int quantity, Double price) {
        this.category = category;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice () {
        return this.price;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product : " + "\n" +
                "category: " + category + "\n" +
                "name: " + name + "\n" +
                "quantity: " + quantity + "\n" +
                "price: " + price + "\n" ;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(category, product.category) && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, price);
    }



}



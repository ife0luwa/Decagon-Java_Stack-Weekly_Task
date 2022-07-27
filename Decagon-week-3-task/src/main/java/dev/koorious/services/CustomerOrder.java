package dev.koorious.services;

import dev.koorious.models.Product;
import dev.koorious.models.User;
import dev.koorious.services.StoreSales;
import dev.koorious.services.StoreStock;

import java.util.ArrayList;

public class CustomerOrder {
    private User customer;
    private StoreStock stock;
    private ArrayList<Product> cart;


    public CustomerOrder(User customer, StoreStock stock) {
        this.customer = customer;
        this.stock = stock;
        this.cart = new ArrayList<>();
    }


    public ArrayList<Product> getCart() {
        return cart;
    }


    /**
     * @param productName
     * @param quantity
     * this method uses getMatchIndex to check the availability of the product,
     * then create a Product object with the return value
     * then checks for sufficient balance and quantity
     * finally adds a new aproduct object into the cart
     */
    public void addToCart (String productName, int quantity) {
        int index = stock.getMatchIndex(productName);
        if (index >= 0) {
            Product newPro = stock.getInventories().get(index);
            if(customer.getBalance() >= (quantity * newPro.getPrice()) && quantity <= newPro.getQuantity()) {
            cart.add(new Product(newPro.getCategory(), productName, quantity, newPro.getPrice()));
            }
            else System.out.println("insufficient funds or product out of stock");
        }
        else {
            System.out.println("Product not available");
        }
    }



    /**
     * @param sales (in order to access our totalOrders array list)
     * this method sends all the orders into the totalOrders array list
     */
    public void placeOrder(StoreSales sales) {
        for(Product eachOrder : cart) {
            sales.getTotalOrders().add(eachOrder);
        }
    }



}

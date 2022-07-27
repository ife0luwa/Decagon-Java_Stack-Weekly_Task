package dev.koorious.service;


import dev.koorious.enums.Roles;
import dev.koorious.models.Product;
import dev.koorious.models.User;

public class StoreSales extends StoreStock {
    private StoreStock myStoreStock;
    private Product customerCart;

    public StoreSales(StoreStock myStoreStock) {
        this.myStoreStock = myStoreStock;
        this.customerCart = new Product();
    }




    public Product sell (User customer, String item, int quantity) {
        if(customer.getRole() == Roles.CUSTOMER) {
            for(Product eachProduct : myStoreStock.inventories) {
                if(eachProduct.getName().equalsIgnoreCase(item)) {
                    if (eachProduct.getQuantity() <= 0 || eachProduct.getQuantity() < quantity) {
                        System.out.println("Not enough available product or Product out of stock!");
                    }
                    else if((eachProduct.getPrice() * quantity) <= customer.getBalance()) {
                        eachProduct.setQuantity(eachProduct.getQuantity() - quantity);
                        customerCart = new Product(eachProduct.getCategory(),item,quantity, eachProduct.getPrice() * quantity);
                        break;
                    }
                }
            }
        }
        else {
            System.out.println("Only customers can purchase goods");
        }
        return customerCart;

    }

    public String printReceipt () {
        String output = "";
        if (customerCart.getPrice() == null || customerCart.getName() == null || customerCart.getQuantity() == 0) {
            output ="No purchase!";
        }
        else {
            output =
                    "\t" + "\t" + "    koorius store" + "\t" + "\t" + "\n" +
                            "\t" + "    Plot 24,Jericho hills" + "\t" + "\n" +
                            "\t" + "       Clements Island" + "\t" + "\n" +
                            "* * * thanks for shopping with us * * *" + "\n" + "\n" +
                            "item" + "\t" + "\t" +  "\t" +  "price" + "\t" + "  quantity" + "\n" +
                             customerCart.getName() + "\t" + "\t" + "    " + (customerCart.getPrice() / customerCart.getQuantity()) + "\t" + "  " +customerCart.getQuantity() + "\t" + "\n" + "\n" +
                            "\t" + "   SUBTOTAL" + "\t" + "\t" + "$" + customerCart.getPrice() + "\t" + "\n" +
                            "* * * * * * * * * * * * * * * * * * * *";
        }
        return output;
        }





}

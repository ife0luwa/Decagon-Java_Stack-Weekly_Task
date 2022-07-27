package models;

import enums.Roles;
import models.Product;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store {
    private CopyOnWriteArrayList<Product> inventories;

    public Store() {
        this.inventories = new CopyOnWriteArrayList<>();
    }


    public void setInventories(CopyOnWriteArrayList<Product> inventories) {
        this.inventories = inventories;
    }



    public List<Product> reStock (Product myProduct) {
        inventories.add(myProduct);
        return inventories;
    }


    public String sell (User myUser, User customer, String item) {
        String result = "";
        if(myUser.getRole() == Roles.CASHIER) {
            for(Product inventory : inventories) {
                if(inventory.getName().equals(item) && inventory.getPrice() <= customer.getBalance()) {
                    inventories.remove(inventory);
                    result = item + " sale successful";
                }
                else {
                    result =  item +  " is out of stock!";
                }
            }
        } else {

            result =  "Staff member can't purchase goods";
        }

        return result;
    }



    public void checkStock () {
        System.out.println("* * * Stock list * * *");
        if(inventories.isEmpty()) {
            System.out.println("Stock is empty.Restock!");
        }
        for(Product inventory : inventories) {
            System.out.println("Item:" + inventory.getName() + "\n" + "Price:" + inventory.getPrice() );
        }
    }


}

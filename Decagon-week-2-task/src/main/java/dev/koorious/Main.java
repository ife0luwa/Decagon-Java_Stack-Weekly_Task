package dev.koorious;

import dev.koorious.enums.Roles;
import dev.koorious.models.User;
import dev.koorious.service.StoreHire;
import dev.koorious.service.StoreSales;
import dev.koorious.service.StoreStock;

public class Main {

    public static void main(String[] args) {

        StoreStock villageGrocery = new StoreStock();
        villageGrocery.reStock();
        villageGrocery.printProducts();
        //System.out.println(villageGrocery.search("banana"));



        User manager = new User("Kola", Roles.MANAGER);

        StoreHire hr = new StoreHire();
        User cashierOby = new User("Oby", Roles.APPLICANT);
        hr.hire(manager, cashierOby);
        //System.out.println();


        User customer = new User("Hassan", Roles.CUSTOMER);
        customer.loadWallet(10_000.0);
        System.out.println();


        StoreSales makeSales = new StoreSales(villageGrocery);
        makeSales.sell(customer, "Whole Wheat", 958);
        //villageGrocery.printProducts();
       System.out.println(makeSales.printReceipt());

        //villageGrocery.printProducts();




    }











}

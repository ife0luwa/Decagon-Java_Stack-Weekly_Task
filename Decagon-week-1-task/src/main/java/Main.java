import enums.Roles;
import models.Product;
import models.Store;
import models.User;
import services.StoreHire;

public class Main {
    public static void main(String[] args) {
        Store villageGrocery = new Store();
        Product myStock = new Product("rice", 10.40);

        villageGrocery.reStock(myStock);
        villageGrocery.checkStock();


        User manager = new User("Kola", Roles.MANAGER);

       // System.out.println("Welcome " + manager.getName() + ", our new " + manager.getRole() + "\n");

        StoreHire hr = new StoreHire();
//        System.out.println(hr.hire(manager, new models.User("Oby", Roles.APPLICANT)));
//        System.out.println();


        User customer = new User("Hassan", Roles.CUSTOMER);
        customer.loadWallet(200.0);
//        System.out.println("Welcome " + customer.getName() + ", village grocery at your service");

//        System.out.println(villageGrocery.sell(new models.User("oby", Roles.CASHIER), customer, "rice"));

        villageGrocery.checkStock();


    }
}

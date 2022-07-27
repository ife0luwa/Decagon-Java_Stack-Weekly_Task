import dev.koorius.model.Product;
import dev.koorius.enums.Roles;
import dev.koorius.model.User;
import dev.koorius.service.*;

public class Main {



    public static void main(String[] args) {

        StoreStock villageGrocery = new StoreStock();
        villageGrocery.reStock();

        //villageGrocery.printProducts();
        //System.out.println(villageGrocery.search("carrot"));



        User manager = new User("Kola", Roles.MANAGER);

        StoreHire hr = new StoreHire();
        User cashierOby = new User("Oby", Roles.APPLICANT);
        hr.hire(manager, cashierOby);
        //System.out.println();


        User customer = new User("Hassan", Roles.CUSTOMER);
        customer.loadWallet(10_000.0);
        System.out.println();


//        service.CustomerOrder firstOrder = new service.CustomerOrder(customer, villageGrocery);
//        service.CustomerOrder secondOrder = new service.CustomerOrder(customer, villageGrocery);
//        service.CustomerOrder thirdOrder = new service.CustomerOrder(customer, villageGrocery);
//        service.CustomerOrder fourthOrder = new service.CustomerOrder(customer, villageGrocery);
        CustomerOrder fifthOrder = new CustomerOrder(customer, villageGrocery);



//       firstOrder.addToCart("carrot", 200);
//       firstOrder.addToCart("Oatmeal Raisin", 25);
//       firstOrder.addToCart("whole wheat", 23);


//        secondOrder.addToCart("carrot", 1800);
//       secondOrder.addToCart("Oatmeal Raisin", 15);
//        secondOrder.addToCart("whole wheat", 12);

//        thirdOrder.addToCart("carrot", 150);
//        thirdOrder.addToCart("whole wheat", 5);
//        thirdOrder.addToCart("Potato Chips", 30);

//        fourthOrder.addToCart("Oatmeal Raisin", 50);
//        fourthOrder.addToCart("Potato Chips", 35);
//        fourthOrder.addToCart("whole wheat", 2);

//        fifthOrder.addToCart("Oatmeal Raisin", 55);
//        fifthOrder.addToCart("carrot", 250);
//        fifthOrder.addToCart("whole wheat", 15);

        for(int i = 0; i < 4187; i++) {
            fifthOrder.addToCart("carrot", 1);
        }

        StoreSales makeSales = new StoreSales(villageGrocery);


//        firstOrder.placeOrder(makeSales);
//        secondOrder.placeOrder(makeSales);
//        thirdOrder.placeOrder(makeSales);
//        fourthOrder.placeOrder(makeSales);
        fifthOrder.placeOrder(makeSales);
//        //System.out.println(makeSales.getAllOrders());
//        System.out.println(makeSales.getAllOrders());


        Product carrot = villageGrocery.getInventories().get(villageGrocery.getMatchIndex("carrot"));

        makeSales.loadQueue();
//        System.out.println(makeSales.sell());;
//        System.out.println();
//        System.out.println(makeSales.sell());

        System.out.println("before sales");
        System.out.println( carrot);
        System.out.println("=============================");


        int len = 2000;
        for(int i = 0; i < len; i++) {
            StoreSalesThread sales4 = new StoreSalesThread(makeSales);
            StoreSalesThread sales5 = new StoreSalesThread(makeSales);

            sales4.start();
            sales5.start();

            try {
                sales5.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("number of orders: " + len*2);
        System.out.println();

        System.out.println("after sales");
        System.out.println((carrot));














    }











}

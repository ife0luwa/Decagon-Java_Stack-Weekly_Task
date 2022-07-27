package dev.koorius.service;

import dev.koorius.model.Product;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class StoreSales extends StoreStock {
    private StoreStock myStoreStock;
    private Product customerCart;
    private CopyOnWriteArrayList<Product> totalOrders;
    private LinkedList<Product> orderList;


    /** create a comparator to compare products by quantities in descending order */
    static final Comparator<Product> QUANTITY_WISE = new Comparator<Product>() {
        @Override
        public int compare(Product product1, Product product2) {
            int result = 0;
            if (product1.getQuantity() > product2.getQuantity()) {
                result = -1;
            } else if (product1.getQuantity() < product2.getQuantity()) {
                result = 1;
            } else {
                result = 0;
            }
            return result;
        }
    };




    public StoreSales(StoreStock myStoreStock) {
        this.myStoreStock = myStoreStock;
        this.customerCart = new Product();
        this.totalOrders = new CopyOnWriteArrayList<>();
        this.orderList = new LinkedList<>();

    }


    /** while totalOrders (arrayList containing every customer order)
     * this method creates a temporary arrayList
     * and add the first element of totalOrders[o]
     * then loops thru totalOrders to get products with same name as totalOrders[0]
     * and add them into temp where they are sorted by QUANTITY_WISE
     * then pushed into the final queue orderList
     */
    public void loadQueue () {
    while (!totalOrders.isEmpty()) {
        CopyOnWriteArrayList<Product> temp = new CopyOnWriteArrayList<>();
        temp.add(totalOrders.get(0));
        totalOrders.remove(0);
        for (Product x : totalOrders) {
            if (x.getName().equalsIgnoreCase(temp.get(0).getName())) {
                temp.add(x);
                totalOrders.remove(x);
            }
        }
            temp.sort(QUANTITY_WISE);
            orderList.addAll(temp);
    }
//            orderList.forEach(System.out::println);
        //System.out.println(orderList.size());
}


/** this method pops the top element(model.Product) in the LinkedList orderList */
/** then gets the name matching product with productIndex  */
/** then updates the quantity of the product
 * returns model.Product object
 * */
   synchronized public String  sell () {
        ReentrantLock productLock = new ReentrantLock();
        Product myOrder = new Product();
        if (!orderList.isEmpty()) {
            myOrder = orderList.remove();
            int productIndex = myStoreStock.getMatchIndex(myOrder.getName());
            /*  the sell method is modifying the product quantity (resource/data) here */
            try {
                productLock.lock();
                myStoreStock.inventories.get(productIndex).setQuantity(myStoreStock.inventories.get(productIndex).getQuantity() - myOrder.getQuantity());
            }
            finally {
                productLock.unlock();
            }
            return printReceipt(myOrder);
        } else {
            return "Order list is empty";
        }

    }


    public String printReceipt (Product customerCart) {
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
                             customerCart.getName() + "\t" + "\t" + "    " + String.format("%.2f", customerCart.getPrice()) + "\t" + "  " +customerCart.getQuantity() + "\t" + "\n" + "\n" +
                            "\t" + "   SUBTOTAL" + "\t" + "\t" + "$" + customerCart.getPrice() * customerCart.getQuantity() + "\t" + "\n" +
                            "* * * * * * * * * * * * * * * * * * * *";
        }
        return output;
        }


    public CopyOnWriteArrayList<Product> getTotalOrders() {
        return totalOrders;
    }

    public LinkedList<Product> getOrderList() {
        return orderList;
    }

    public StoreStock getMyStoreStock() {
        return myStoreStock;
    }
}

import enums.Roles;
import models.Product;
import models.Store;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StoreTest {

    static Product myProduct ;
    static CopyOnWriteArrayList<Product> inventories;
    static User myUser;
    static String item;

    static Store store;
    static User myCustomer;

    @BeforeEach
    void setUp() {
        myProduct = new Product("rice", 50.0);
        inventories = new CopyOnWriteArrayList<>();
        myUser = new User("rose", Roles.CASHIER);
        item = "rice";
        store = new Store();
        store.setInventories(inventories);
        myCustomer = new User("ladi", Roles.CUSTOMER);
        myCustomer.loadWallet(200.3);
    }

    @Test
    void reStock() {
        int len = inventories.size();
        inventories.add(myProduct);
        assertEquals(inventories.get(inventories.size() - 1), myProduct);
        assertEquals(len + 1, inventories.size());

    }

    @Test
    void sell() {
        inventories.add(myProduct);
        String expectedValue = item + " sale successful";
        String actualValue = store.sell(myUser, myCustomer, item);
        assertEquals(expectedValue, actualValue);

    }

    @Test
    void checkStock() {
    }
}
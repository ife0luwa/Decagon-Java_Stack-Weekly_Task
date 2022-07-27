package week2task;

import dev.koorious.enums.Roles;
import dev.koorious.models.Product;
import dev.koorious.models.User;
import dev.koorious.service.StoreSales;
import dev.koorious.service.StoreStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreSalesTest {
    private static User myUser;
    private static Product myProduct;
    private static StoreSales mySales;
    private static StoreStock myStock;


    @BeforeEach
    void setUp() {
        myUser = new User("Olaide", Roles.CUSTOMER);
        myUser.loadWallet(1000.0);
        myProduct = new Product("crackers", "Whole Wheat", 200, (3.49 * 200));
        myStock = new StoreStock();
        myStock.reStock();
        mySales = new StoreSales(myStock);
    }

    @Test
    void sell() {
        assertEquals(myProduct, mySales.sell(myUser, "Whole Wheat", 200));
    }
}
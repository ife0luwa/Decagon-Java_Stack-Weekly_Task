package week2task;

import dev.koorious.enums.Roles;
import dev.koorious.models.Product;
import dev.koorious.models.User;
import dev.koorious.services.CustomerOrder;
import dev.koorious.services.StoreSales;
import dev.koorious.services.StoreStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreSalesTest {
    private static User myUser;
//    private static Product myProduct;
    private static StoreSales mySales;
    private static StoreStock myStock;
    private static CustomerOrder customerOrder;


    @BeforeEach
    void setUp() {
        myUser = new User("Olaide", Roles.CUSTOMER);
        myUser.loadWallet(10000.0);
        myStock = new StoreStock();
        myStock.reStock();
        mySales = new StoreSales(myStock);

        customerOrder = new CustomerOrder(myUser, myStock);
        customerOrder.addToCart("carrot", 20);
        customerOrder.placeOrder(mySales);
        mySales.loadQueue();
    }


    @Test
    void sell() {
        /** test 1 checking for the length of sales queue */
        int initialSize = mySales.getOrderList().size();

        /** test 2 checking for reduction in product quantity after calling sell */
        Product prod = myStock.getInventories().get(myStock.getMatchIndex("carrot"));
        int initialQuantity = prod.getQuantity();

        mySales.sell();

        assertEquals(initialSize - 1, mySales.getOrderList().size()); /** test 1 */

        assertEquals(initialQuantity - 20, prod.getQuantity());  /** test 2 */
    }

    @Test
    void loadQueue() {
    }
}
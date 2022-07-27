package week2task;

import dev.koorious.enums.Roles;
import dev.koorious.models.User;
import dev.koorious.services.CustomerOrder;
import dev.koorious.services.StoreStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderTest {
    User customer;
    StoreStock stock;
    CustomerOrder customerOrder;

    @BeforeEach
    void setUp() {
        customer = new User("Zion", Roles.CUSTOMER);
        customer.loadWallet(10_000.00);
        stock = new StoreStock();
        stock.reStock();
        customerOrder = new CustomerOrder(customer, stock);

    }

    @Test
    void addToCart() {
        int expectedSize = customerOrder.getCart().size();
        customerOrder.addToCart("carrot", 20);
        assertNotEquals(expectedSize, customerOrder.getCart().size());
    }

    @Test
    void placeOrder() {
    }
}
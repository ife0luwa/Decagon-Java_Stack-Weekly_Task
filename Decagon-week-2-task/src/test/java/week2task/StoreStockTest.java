package week2task;

import dev.koorious.models.Product;
import dev.koorious.service.StoreStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class  StoreStockTest {
    private static StoreStock myStock;

    @BeforeEach
    void setUp() {
        myStock = new StoreStock();
        myStock.reStock();
    }

    @Test
    void categorize() {
        String cate = "bars";
        ArrayList myCategory = new ArrayList<>();
        for (Product eachProduct : myStock.getInventories()) {
            if (eachProduct.getCategory().equalsIgnoreCase(cate)) {
                myCategory.add(eachProduct);
            }
        }
        int expectedValue = myCategory.size();
        assertEquals(expectedValue, myStock.categorize(cate).size());
    }

    @Test
    void search() {
        boolean expectedValue = true;
        assertEquals(expectedValue, myStock.search("Whole Wheat"));

    }

    @Test
    void getMatchIndex() {
        String item = "carrot";
        int expectedValue = 0;
        for(Product inv : myStock.getInventories()) {
            if(inv.getName().equalsIgnoreCase(item)) {
                expectedValue = myStock.getInventories().indexOf(inv);
            }
        }
        assertEquals(expectedValue, myStock.getMatchIndex(item));
    }
}
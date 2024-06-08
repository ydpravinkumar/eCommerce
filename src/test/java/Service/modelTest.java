package Service;

import org.junit.jupiter.api.Test;
import com.example.supplychainback.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class modelTest {

    @Test
    public void testIdSetterGetter() {
        Product product = new Product();
        product.setId(1L);
        assertEquals(1L, product.getId(), "The id should be 1");
    }

    @Test
    public void testPriceSetterGetter() {
        Product product = new Product();
        product.setPrice(199.99);
        assertEquals(199.99, product.getPrice(), 0.001, "The price should be 199.99");
    }
}

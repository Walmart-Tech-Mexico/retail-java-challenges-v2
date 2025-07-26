import org.junit.jupiter.api.Test;

import com.cart.Cart;
import com.cart.DiscountService;
import com.cart.Product;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testTotalWithDiscounts() {
        Product p1 = new Product("Leche", 2.0, 3, true);
        Product p2 = new Product("Pan", 1.5, 2, false);
        Product p3 = new Product("Huevos", 3.0, 3, true);
        Product p4 = new Product("Tortilas", 3.0, 3, false);
        Cart cart = new Cart(Arrays.asList(p1, p2, p3, p4), new DiscountService());

        double total = cart.calculateTotal();
        assertEquals(2.0 * 3 * 0.9 + 1.5 * 2 + 3.0 * 3 * 0.9 + 3.0 * 3, total, 0.01);
    }
}

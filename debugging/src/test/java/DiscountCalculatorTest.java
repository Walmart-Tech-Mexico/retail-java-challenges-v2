import org.junit.jupiter.api.Test;

import com.discount.Customer;
import com.discount.DiscountCalculator;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountCalculatorTest {

    @Test
    public void testGoldCustomerGetsDiscount() {
        Customer customer = new Customer("Ana", "Gold");
        DiscountCalculator calculator = new DiscountCalculator();
        double discount = calculator.calculateDiscount(customer);
        assertEquals(20.0, discount, 0.01); // Espera 20% de descuento
    }

    @Test
    public void testNonGoldCustomerGetsNoDiscount() {
        Customer customer = new Customer("Luis", "Silver");
        DiscountCalculator calculator = new DiscountCalculator();
        double discount = calculator.calculateDiscount(customer);
        assertEquals(0.0, discount, 0.01); // Sin descuento
    }
}

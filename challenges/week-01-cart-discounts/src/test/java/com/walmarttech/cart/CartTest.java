package com.walmarttech.cart;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

//public class CartTest {
//
//    @Test
//    public void testTotalWithDiscounts() {
//        Product p1 = new Product("Leche", 2.0, 3, true);
//        Product p2 = new Product("Pan", 1.5, 2, false);
//        Product p3 = new Product("Huevos", 3.0, 3, true);
//
//        Cart cart = new Cart(Arrays.asList(p1, p2, p3), new DiscountService());
//
//        double total = cart.calculateTotal();
//        assertEquals(2.0 * 3 * 0.9 + 1.5 * 2 + 3.0 * 3 * 0.9, total, 0.01);
//    }
//}

import com.walmarttech.cart.strategies.VolumeDiscountStrategy;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {
    private Product p1, p2, p3;
    private Cart cart;

    @BeforeEach
    void setUp() {
        p1 = new Product("Leche", 2.0, 3, true); // Debería aplicar descuento
        p2 = new Product("Pan",  1.5, 2, false); // Sin descuento (cantidad < 3), (hasDiscount=false)
        p3 = new Product("Huevos", 3.0, 3, true); // Debería aplicar descuento
        cart = new Cart(Arrays.asList(p1, p2, p3), new DiscountService(new VolumeDiscountStrategy()));
    }

    @Test
    void testThreeProductsWithDiscount() {
        // p1: 2.0 * 3 * 0.9 = 5.4
        assertEquals(5.4, new DiscountService(new VolumeDiscountStrategy()).applyDiscount(p1), 0.01);
        // p3: 3.0 * 3 * 0.9 = 8.1
        assertEquals(8.1, new DiscountService(new VolumeDiscountStrategy()).applyDiscount(p3), 0.01);
    }

    @Test
    void testProductsWithoutDiscount() {
        // p2: 1.5 * 2 = 3.0 (sin descuento por cantidad < 3)
        assertEquals(3.0, new DiscountService(new VolumeDiscountStrategy()).applyDiscount(p2), 0.01);
    }

    @Test
    void testTotalCorrect() {
        // Total: 5.4 (p1) + 3.0 (p2) + 8.1 (p3) = 16.5
        assertEquals(16.5, cart.calculateTotal(), 0.01);
    }
}
package com.walmarttech.cart;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private static final double TEN_PERCENT_DISCOUNT = 0.9;

    @Test
    public void testTotalWithDiscounts() {
        Product milk = new Product("Leche", 2.0, 3, true);
        Product bread = new Product("Pan", 1.5, 2, false);
        Product eggs = new Product("Huevos", 3.0, 3, true);

        double discountedTotalPriceForMilk = milk.getTotalPrice() * TEN_PERCENT_DISCOUNT;
        double totalPriceForBread = bread.getTotalPrice();
        double discountedTotalPriceForEggs = eggs.getTotalPrice() * TEN_PERCENT_DISCOUNT;

        Cart cart = new Cart(Arrays.asList(milk, bread, eggs), new DiscountService());

        double total = cart.calculateTotal();
        assertEquals(discountedTotalPriceForMilk + totalPriceForBread + discountedTotalPriceForEggs, total, 0.01);
    }

    @Test
    public void testThreeProductsTotalWithAppliedDiscounts() {
        Product milk = new Product("Leche", 2.0, 3, true);
        Product bread = new Product("Pan", 1.5, 5, true);
        Product eggs = new Product("Huevos", 3.0, 8, true);

        double discountedTotalPriceForMilk = milk.getTotalPrice() * TEN_PERCENT_DISCOUNT;
        double discountedTotalPriceForBread = bread.getTotalPrice() * TEN_PERCENT_DISCOUNT;
        double discountedTotalPriceForEggs = eggs.getTotalPrice() * TEN_PERCENT_DISCOUNT;

        Cart cart = new Cart(Arrays.asList(milk, bread, eggs), new DiscountService());

        double total = cart.calculateTotal();
        assertEquals(discountedTotalPriceForMilk + discountedTotalPriceForBread + discountedTotalPriceForEggs, total,
                0.01);
    }

    @Test
    public void testTotalWithNoDiscounts() {
        Product milk = new Product("Leche", 2.0, 3, false);
        Product bread = new Product("Pan", 1.5, 2, false);
        Product eggs = new Product("Huevos", 3.0, 3, false);

        Cart cart = new Cart(Arrays.asList(milk, bread, eggs), new DiscountService());

        double total = cart.calculateTotal();
        assertEquals(milk.getTotalPrice() + bread.getTotalPrice() + eggs.getTotalPrice(), total,
                0.01);
    }

    @Test
    public void testDiscountDoesntApplyIfQuantityIsBelowThreshold() {
        Product bread = new Product("Pan", 1.5, 2, true);

        Cart cart = new Cart(Arrays.asList(bread), new DiscountService());

        double total = cart.calculateTotal(); // Precio: 3, no se aplica descuento
        assertEquals(bread.getTotalPrice(), total, 0.01); // Precio total (3) = Total calculado por el carrito (3)
    }
}

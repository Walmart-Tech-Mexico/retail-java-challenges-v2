package com.walmarttech.discount;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountCalculatorTest {

    @Test
    public void testGoldCustomerGetsDiscount() {
        Customer customer = new Customer("Ana", MembershipLevel.GOLD);
        DiscountCalculator calculator = new DiscountCalculator();
        double discount = calculator.calculateDiscount(customer, 100.0);
        assertEquals(20.0, discount, 0.01); // Espera 20% de descuento
    }

    @Test
    public void testNonGoldCustomerGetsNoDiscount() {
        Customer customer = new Customer("Luis", MembershipLevel.SILVER);
        DiscountCalculator calculator = new DiscountCalculator();
        double discount = calculator.calculateDiscount(customer, 100.0);
        assertEquals(0.0, discount, 0.01); // Sin descuento
    }

    @Test
    public void testGoldCustomerGetsDiscountWithNewString() {
        MembershipLevel membershipLevel = MembershipLevel.GOLD;
        Customer customer = new Customer("Ana", membershipLevel);
        DiscountCalculator calculator = new DiscountCalculator();
        double discount = calculator.calculateDiscount(customer, 100.0);
        assertEquals(20.0, discount, 0.01); // Espera 20% de descuento
    }
}

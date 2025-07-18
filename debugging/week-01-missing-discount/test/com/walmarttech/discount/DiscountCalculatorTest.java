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
        // Con el enum cada nivel de membresía puede estar asociado a un porcentaje de descuento específico 
        // En el ejemplo del enum utilicé SILVER(0.1) para demostrar la escalabilidad de la solución
        // Por eso aquí cambié "Silver" por MembershipLevel.NONE, para demostrar que cuando tenga un nivel de membresía con 0% de descuento,
        // no da el descuento
        Customer customer = new Customer("Luis", MembershipLevel.NONE);
        DiscountCalculator calculator = new DiscountCalculator();
        double discount = calculator.calculateDiscount(customer, 100.0);
        
        assertEquals(0.0, discount, 0.01); // Sin descuento
    }
}
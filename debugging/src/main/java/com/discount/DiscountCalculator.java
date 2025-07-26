package com.discount;

public class DiscountCalculator {

        public double calculateDiscount(Customer customer) {
        if (customer.getMembershipLevel() == "Gold") {
            return 20.0; // 20% de descuento
        }
        return 0.0;
    }
}

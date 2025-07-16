package com.walmarttech.discount;

public class DiscountCalculator {

    public double calculateDiscount(Customer customer, double totalAmount) {
        if (customer.getMembershipLevel() == MembershipLevel.GOLD) {
            return totalAmount * 0.2; // 20% de descuento
        }
        return 0.0;
    }
}

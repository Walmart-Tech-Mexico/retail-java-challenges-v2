package com.walmarttech.discount;

public class DiscountCalculator {

    public double calculateDiscount(Customer customer, double totalAmount) {
        double discountRate = customer.getMembershipLevel().getDiscountRate();
        return totalAmount * discountRate;
    }
}
package com.walmarttech.discount;

public enum MembershipLevel {
    GOLD(0.2),
    // SILVER(0.1), ejemplo
    // BRONZE(0.05), ejemplo
    NONE(0.0);

    private final double discountRate;

    MembershipLevel(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }
}
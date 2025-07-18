package com.walmarttech.cart;

public class DiscountService {

    private static final double TEN_PERCENT_DISCOUNT = 0.9;
    private static final int VOLUME_THRESHOLD = 3;

    public double applyDiscount(Product product) {
        if (product.hasDiscount() && product.getQuantity() >= VOLUME_THRESHOLD) {
            return product.getTotalPrice() * TEN_PERCENT_DISCOUNT; // 10% descuento
        }
        return product.getTotalPrice();
    }
}
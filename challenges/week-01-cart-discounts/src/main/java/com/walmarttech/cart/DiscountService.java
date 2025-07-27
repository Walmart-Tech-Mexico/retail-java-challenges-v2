package com.walmarttech.cart;

import com.walmarttech.cart.strategies.DiscountStrategy;

public class DiscountService {
    private final DiscountStrategy discountStrategy;

    public DiscountService(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double applyDiscount(Product product) {
        return discountStrategy.applyDiscount(product);
    }
}

package com.walmarttech.cart.strategies;

import com.walmarttech.cart.Product;

public interface DiscountStrategy {
    double applyDiscount(Product product);
}

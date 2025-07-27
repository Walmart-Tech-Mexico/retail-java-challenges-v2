package com.walmarttech.cart.strategies;

import com.walmarttech.cart.Product;
import com.walmarttech.cart.constants.CartConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VolumeDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(Product product) {
        BigDecimal totalPrice = BigDecimal.valueOf(product.getTotalPrice());
        if (product.isHasDiscount() && product.getQuantity() >= CartConstants.MIN_QUANTITY_FOR_DISCOUNT) {
            totalPrice = totalPrice.multiply(CartConstants.DISCOUNT_FACTOR);
        }
        return totalPrice.setScale(CartConstants.SCALE, RoundingMode.HALF_UP).doubleValue();
    }
}
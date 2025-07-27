package com.walmarttech.cart;

import com.walmarttech.cart.validators.ProductValidator;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class Product {

    private final String name;
    private final double price;
    private final int quantity;
    private final boolean hasDiscount;

    public Product(String name, double price, int quantity, boolean hasDiscount) {
        ProductValidator.validate(name, price, quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.hasDiscount = hasDiscount;
    }

    public double getTotalPrice() {
        return BigDecimal.valueOf(price)
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
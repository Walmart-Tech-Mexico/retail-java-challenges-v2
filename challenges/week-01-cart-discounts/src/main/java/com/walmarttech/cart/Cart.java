package com.walmarttech.cart;

import com.walmarttech.cart.strategies.VolumeDiscountStrategy;

import java.util.List;

public class Cart {
    private final List<Product> products;
    private final DiscountService discountService;

    public Cart(List<Product> products) {
        this.products = products;
        this.discountService = new DiscountService(new VolumeDiscountStrategy());
    }

    public Cart(List<Product> products, DiscountService discountService) {
        this.products = products;
        this.discountService = discountService;
    }

    public double calculateTotal() {
        return products.stream()
                .mapToDouble(p -> discountService.applyDiscount(p))
                .sum();
    }
}

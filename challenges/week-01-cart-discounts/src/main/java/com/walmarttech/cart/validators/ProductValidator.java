package com.walmarttech.cart.validators;

import com.walmarttech.cart.constants.CartConstants;

import java.util.ArrayList;
import java.util.List;

public class ProductValidator {
    public static void validate(String name, double price, int quantity) {
        List<String> errors = new ArrayList<>();

        validatePriceAndQuantity(price, quantity, errors);
        validateName(name, errors);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("; ", errors));
        }
    }

    private static void validatePriceAndQuantity(double price, int quantity, List<String> errors) {
        if (price < 0) {
            errors.add(CartConstants.ERROR_PRICE_NEGATIVE);
        }
        if (quantity < 0) {
            errors.add(CartConstants.ERROR_QUANTITY_NEGATIVE);
        }
    }

    private static void validateName(String name, List<String> errors) {
        if (name == null || name.trim().isEmpty()) {
            errors.add(CartConstants.ERROR_NAME_INVALID);
        }
    }
}
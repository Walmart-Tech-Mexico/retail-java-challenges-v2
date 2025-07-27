package com.walmarttech.cart.constants;

import java.math.BigDecimal;

public final class CartConstants {
    // Constantes para descuentos
    public static final BigDecimal DISCOUNT_FACTOR = BigDecimal.valueOf(0.9); // 10% descuento
    public static final int MIN_QUANTITY_FOR_DISCOUNT = 3;
    public static final int SCALE = 2;

    // Mensajes de error para validaciones
    public static final String ERROR_PRICE_NEGATIVE = "Price must be non-negative";
    public static final String ERROR_QUANTITY_NEGATIVE = "Quantity must be non-negative";
    public static final String ERROR_NAME_INVALID = "Name cannot be null or empty";

    private CartConstants() {
        // Constructor privado para evitar instanciación
    }
}

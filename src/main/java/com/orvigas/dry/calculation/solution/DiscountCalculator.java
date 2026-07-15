package com.orvigas.dry.calculation.solution;

/**
 * Centralizes all discount calculation business logic.
 *
 * <p>This class is the single source of truth for discount rules. All pricing
 * policies are defined here:
 * <ul>
 *   <li>10% discount for orders over $100</li>
 *   <li>Additional 5% discount for bulk purchases (quantity >= 10)</li>
 * </ul>
 *
 * <p>Services (OrderService, InvoiceService, etc.) depend on this calculator
 * instead of reimplementing the formula. Changes to discount rules happen
 * once, in this class. Tests for discount logic are centralized.
 *
 * @author orvigas@gmail.com
 */
public class DiscountCalculator {
    private static final double LARGE_ORDER_THRESHOLD = 100.0;
    private static final double LARGE_ORDER_DISCOUNT = 0.10;
    private static final int BULK_QUANTITY_THRESHOLD = 10;
    private static final double BULK_DISCOUNT = 0.05;

    /**
     * Calculates the final price after applying all applicable discounts.
     *
     * @param basePrice the base price before discounts
     * @param quantity the quantity ordered
     * @return the price after all discounts
     */
    public double calculateFinalPrice(double basePrice, int quantity) {
        double discount = 0;

        if (basePrice > LARGE_ORDER_THRESHOLD) {
            discount += basePrice * LARGE_ORDER_DISCOUNT;
        }

        if (quantity >= BULK_QUANTITY_THRESHOLD) {
            discount += basePrice * BULK_DISCOUNT;
        }

        return basePrice - discount;
    }
}

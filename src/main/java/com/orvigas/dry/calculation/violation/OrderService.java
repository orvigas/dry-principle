package com.orvigas.dry.calculation.violation;

/**
 * Order service with hardcoded discount calculation logic.
 *
 * <p>This service implements discount calculation inline: 10% discount for orders
 * over $100, plus 5% extra for bulk (10+ items). The same logic is duplicated in
 * {@code InvoiceService}.
 *
 * <p>Problems:
 * <ul>
 *   <li>Discount formula is hardcoded in multiple services</li>
 *   <li>If rules change, multiple files must be updated</li>
 *   <li>Different services might implement the formula differently (subtle bugs)</li>
 *   <li>Tests for discount logic are scattered across service tests</li>
 * </ul>
 *
 * <p>See {@code com.orvigas.dry.calculation.solution.OrderService} for the refactored version.
 *
 * @author orvigas@gmail.com
 */
public class OrderService {

    /**
     * Calculates total price with hardcoded discount logic.
     *
     * Rule: 10% discount if amount > $100, plus 5% extra if quantity >= 10.
     */
    public double calculateFinalPrice(double basePrice, int quantity) {
        double discount = 0;

        if (basePrice > 100) {
            discount += basePrice * 0.10;
        }

        if (quantity >= 10) {
            discount += basePrice * 0.05;
        }

        return basePrice - discount;
    }
}

package com.orvigas.dry.calculation.violation;

/**
 * Invoice service with hardcoded discount calculation logic.
 *
 * <p>This service implements the same discount calculation as {@code OrderService}:
 * 10% discount for amounts over $100, plus 5% extra for bulk quantities.
 * The identical logic is duplicated here, violating DRY.
 *
 * <p>Problems:
 * <ul>
 *   <li>Discount formula is repeated, creating maintenance burden</li>
 *   <li>If business rules change, this class might be overlooked</li>
 *   <li>Tests for the same formula must be written multiple times</li>
 *   <li>A new service with the same logic would repeat the calculation again</li>
 * </ul>
 *
 * <p>See {@code com.orvigas.dry.calculation.solution.InvoiceService} for the refactored version.
 *
 * @author orvigas@gmail.com
 */
public class InvoiceService {

    /**
     * Calculates invoice total with hardcoded discount logic (identical to OrderService).
     *
     * Rule: 10% discount if amount > $100, plus 5% extra if quantity >= 10.
     */
    public double calculateInvoiceTotal(double basePrice, int quantity) {
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

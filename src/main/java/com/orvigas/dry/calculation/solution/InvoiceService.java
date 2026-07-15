package com.orvigas.dry.calculation.solution;

/**
 * Invoice service using centralized discount calculation.
 *
 * <p>This service delegates all discount calculations to {@code DiscountCalculator},
 * eliminating the duplicated calculation logic found in
 * {@code com.orvigas.dry.calculation.violation.InvoiceService}.
 *
 * <p>Both OrderService and InvoiceService now use the same {@code DiscountCalculator},
 * ensuring consistent pricing across all business operations.
 *
 * @author orvigas@gmail.com
 */
public class InvoiceService {
    private final DiscountCalculator discountCalculator;

    public InvoiceService(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    /**
     * Calculates invoice total using centralized discount logic.
     */
    public double calculateInvoiceTotal(double basePrice, int quantity) {
        return discountCalculator.calculateFinalPrice(basePrice, quantity);
    }
}

package com.orvigas.dry.calculation.solution;

/**
 * Order service using centralized discount calculation.
 *
 * <p>This service delegates all discount calculations to {@code DiscountCalculator},
 * eliminating the duplicated calculation logic found in
 * {@code com.orvigas.dry.calculation.violation.OrderService}.
 *
 * <p>Benefits:
 * <ul>
 *   <li>No discount formula duplication</li>
 *   <li>Changes to pricing policy happen once in DiscountCalculator</li>
 *   <li>Consistent discount calculations across all services</li>
 *   <li>Simpler to test and maintain</li>
 * </ul>
 *
 * @author orvigas@gmail.com
 */
public class OrderService {
    private final DiscountCalculator discountCalculator;

    public OrderService(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    /**
     * Calculates final price using centralized discount logic.
     */
    public double calculateFinalPrice(double basePrice, int quantity) {
        return discountCalculator.calculateFinalPrice(basePrice, quantity);
    }
}

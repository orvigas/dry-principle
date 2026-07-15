/**
 * Calculation Scenario: Solution (DRY Applied).
 *
 * Classes in this package demonstrate the DRY principle: discount calculation logic
 * is extracted into a reusable {@code DiscountCalculator} class. Services depend on
 * this calculator instead of reimplementing the business rule.
 *
 * <p>Benefits:
 * <ul>
 *   <li>Discount rules are defined once and reused everywhere</li>
 *   <li>Changes to pricing policy happen in one place</li>
 *   <li>Tests for discount logic run once, in {@code DiscountCalculatorTest}</li>
 *   <li>New services automatically use correct calculations</li>
 *   <li>Easier to extend (e.g., add seasonal discounts) without touching services</li>
 * </ul>
 *
 * See {@code com.orvigas.dry.calculation.violation} for the anti-pattern.
 */
package com.orvigas.dry.calculation.solution;

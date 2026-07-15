/**
 * Calculation Scenario: Duplicate business rule calculations across services.
 *
 * <h2>The Problem</h2>
 *
 * In the {@code violation} package, multiple services (OrderService, InvoiceService)
 * each implement their own discount calculation logic. The formulas are duplicated:
 * "apply 10% discount for orders over $100, plus an additional 5% for bulk purchases."
 * If the business rule changes, multiple services need updating. Changes are easily
 * forgotten in one service, creating inconsistent behavior. Tests for discount logic
 * are split across multiple test classes.
 *
 * <h2>The Solution</h2>
 *
 * In the {@code solution} package, a {@code DiscountCalculator} class encapsulates
 * all discount logic. Services now depend on this calculator instead of reimplementing
 * the formula. Changes to discount rules happen once. Tests for discount logic are
 * centralized. New services automatically use the correct calculation.
 *
 * <h2>Key Insight</h2>
 *
 * Business rule duplications often occur silently—each service "owns" its version
 * of the calculation, and inconsistencies accumulate slowly. Extraction makes the
 * rule a single source of truth, preventing divergent implementations.
 */
package com.orvigas.dry.calculation;

package com.orvigas.dry.calculation.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the centralized DiscountCalculator.
 *
 * <p>All discount calculation logic is tested in one place. Changes to business
 * rules are verified here. Services that use the calculator automatically benefit
 * from these tests without needing to duplicate calculation test cases.
 *
 * @author orvigas@gmail.com
 */
class DiscountCalculatorTest {
    private DiscountCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DiscountCalculator();
    }

    @Test
    void noDiscountForSmallOrder() {
        double price = calculator.calculateFinalPrice(50, 5);
        assertThat(price).isEqualTo(50.0);
    }

    @Test
    void appliesToLargeOrderDiscount() {
        double price = calculator.calculateFinalPrice(150, 5);
        assertThat(price).isEqualTo(135.0);
    }

    @Test
    void appliesBulkDiscount() {
        double price = calculator.calculateFinalPrice(80, 10);
        assertThat(price).isEqualTo(76.0);
    }

    @Test
    void appliesBothDiscounts() {
        double price = calculator.calculateFinalPrice(150, 10);
        assertThat(price).isEqualTo(127.5);
    }

    @Test
    void largeOrderDiscountIsExactlyTenPercent() {
        double basePrice = 200;
        double price = calculator.calculateFinalPrice(basePrice, 1);
        double expectedDiscount = basePrice * 0.10;
        assertThat(price).isEqualTo(basePrice - expectedDiscount);
    }

    @Test
    void bulkDiscountIsExactlyFivePercent() {
        double basePrice = 50;
        double price = calculator.calculateFinalPrice(basePrice, 10);
        double expectedDiscount = basePrice * 0.05;
        assertThat(price).isEqualTo(basePrice - expectedDiscount);
    }

    @Test
    void discountsAreAdditive() {
        double basePrice = 200;
        double price = calculator.calculateFinalPrice(basePrice, 10);
        double expectedTotalDiscount = (basePrice * 0.10) + (basePrice * 0.05);
        assertThat(price).isEqualTo(basePrice - expectedTotalDiscount);
    }

    @Test
    void zeroQuantityReceivesLargeOrderDiscount() {
        double price = calculator.calculateFinalPrice(150, 0);
        assertThat(price).isEqualTo(135.0);
    }

    @Test
    void boundaryAtLargeOrderThreshold() {
        double price = calculator.calculateFinalPrice(100, 5);
        assertThat(price).isEqualTo(100.0);

        double priceJustOver = calculator.calculateFinalPrice(100.01, 5);
        assertThat(priceJustOver).isLessThan(100.01);
    }

    @Test
    void boundaryAtBulkQuantityThreshold() {
        double price = calculator.calculateFinalPrice(150, 9);
        assertThat(price).isEqualTo(135.0);

        double priceWithBulk = calculator.calculateFinalPrice(150, 10);
        assertThat(priceWithBulk).isLessThan(135.0);
    }
}

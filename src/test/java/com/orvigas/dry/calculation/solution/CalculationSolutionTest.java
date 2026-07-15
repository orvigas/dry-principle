package com.orvigas.dry.calculation.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the calculation solution scenario.
 *
 * <p>This test demonstrates the benefit of extracting discount logic: tests for the
 * calculation are centralized in {@code DiscountCalculatorTest}. Services just verify
 * they delegate to the calculator correctly. Adding a new service with discount logic
 * requires no new discount tests—it automatically uses the same, well-tested calculator.
 *
 * @author orvigas@gmail.com
 */
class CalculationSolutionTest {
    private DiscountCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new DiscountCalculator();
    }

    @Test
    void orderServiceUsesCalculatorForPricing() {
        OrderService service = new OrderService(calculator);
        double finalPrice = service.calculateFinalPrice(150, 5);

        assertThat(finalPrice).isEqualTo(135.0);
    }

    @Test
    void orderServiceDelegatesBulkDiscount() {
        OrderService service = new OrderService(calculator);
        double finalPrice = service.calculateFinalPrice(80, 10);

        assertThat(finalPrice).isEqualTo(76.0);
    }

    @Test
    void invoiceServiceUsesCalculatorForPricing() {
        InvoiceService service = new InvoiceService(calculator);
        double finalPrice = service.calculateInvoiceTotal(150, 5);

        assertThat(finalPrice).isEqualTo(135.0);
    }

    @Test
    void invoiceServiceDelegatesBulkDiscount() {
        InvoiceService service = new InvoiceService(calculator);
        double finalPrice = service.calculateInvoiceTotal(80, 10);

        assertThat(finalPrice).isEqualTo(76.0);
    }

    @Test
    void demonstratesConsistentCalculationAcrossServices() {
        OrderService orderService = new OrderService(calculator);
        InvoiceService invoiceService = new InvoiceService(calculator);

        double orderPrice = orderService.calculateFinalPrice(200, 15);
        double invoicePrice = invoiceService.calculateInvoiceTotal(200, 15);

        assertThat(orderPrice).isEqualTo(invoicePrice);
    }
}

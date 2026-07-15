package com.orvigas.dry.calculation.violation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the calculation violation scenario.
 *
 * <p>This test demonstrates that the violation "works" (calculations happen),
 * but at the cost of duplicated discount formulas. Tests for discount logic must
 * be written separately for OrderService and InvoiceService, even though the logic
 * is identical. If business rules change, both services must be updated.
 *
 * @author orvigas@gmail.com
 */
class CalculationViolationTest {

    @Test
    void orderServiceCalculatesDiscountForLargeOrder() {
        OrderService service = new OrderService();
        double finalPrice = service.calculateFinalPrice(150, 5);

        assertThat(finalPrice).isEqualTo(135.0);
    }

    @Test
    void orderServiceCalculatesBulkDiscount() {
        OrderService service = new OrderService();
        double finalPrice = service.calculateFinalPrice(80, 10);

        assertThat(finalPrice).isEqualTo(76.0);
    }

    @Test
    void orderServiceAppliesBothDiscounts() {
        OrderService service = new OrderService();
        double finalPrice = service.calculateFinalPrice(150, 10);

        assertThat(finalPrice).isEqualTo(127.5);
    }

    @Test
    void invoiceServiceCalculatesDiscountForLargeOrder() {
        InvoiceService service = new InvoiceService();
        double finalPrice = service.calculateInvoiceTotal(150, 5);

        assertThat(finalPrice).isEqualTo(135.0);
    }

    @Test
    void invoiceServiceCalculatesBulkDiscount() {
        InvoiceService service = new InvoiceService();
        double finalPrice = service.calculateInvoiceTotal(80, 10);

        assertThat(finalPrice).isEqualTo(76.0);
    }

    @Test
    void invoiceServiceAppliesBothDiscounts() {
        InvoiceService service = new InvoiceService();
        double finalPrice = service.calculateInvoiceTotal(150, 10);

        assertThat(finalPrice).isEqualTo(127.5);
    }

    @Test
    void demonstratesDuplicateCalculationLogic() {
        OrderService orderService = new OrderService();
        InvoiceService invoiceService = new InvoiceService();

        double orderPrice = orderService.calculateFinalPrice(200, 15);
        double invoicePrice = invoiceService.calculateInvoiceTotal(200, 15);

        assertThat(orderPrice).isEqualTo(invoicePrice);
    }
}

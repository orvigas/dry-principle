package com.orvigas.dry.configuration.violation;

import java.util.HashMap;
import java.util.Map;

/**
 * Order service with hardcoded configuration values (magic numbers).
 *
 * <p>This service demonstrates code duplication: configuration values like
 * max retry attempts and discount percentage are hardcoded here, duplicating
 * logic from {@code UserService} and other services.
 *
 * <p>Problems:
 * <ul>
 *   <li>If retry limit changes from 3 to 5, multiple files must be updated</li>
 *   <li>Different services might use different retry limits (subtle bugs)</li>
 *   <li>No single place to document retry policy</li>
 *   <li>Testing different configurations requires modifying source code</li>
 * </ul>
 *
 * <p>See {@code com.orvigas.dry.configuration.solution.OrderService} for the refactored version.
 *
 * @author orvigas@gmail.com
 */
public class OrderService {
    private final Map<String, String> orders = new HashMap<>();

    /**
     * Processes an order with hardcoded retry limit and discount.
     */
    public String processOrder(String userId, double amount) throws OrderException {
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                Thread.sleep(attempt * 1000);
                double discountedAmount = amount * 0.9;
                String orderId = "ORD_" + userId + "_" + System.currentTimeMillis();
                orders.put(orderId, "PENDING");
                return orderId;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        throw new OrderException("Failed to process order after 3 attempts");
    }

    public static class OrderException extends Exception {
        public OrderException(String message) {
            super(message);
        }
    }
}

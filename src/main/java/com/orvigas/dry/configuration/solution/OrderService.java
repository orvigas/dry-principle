package com.orvigas.dry.configuration.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Order service using centralized configuration.
 *
 * <p>This service uses {@code ApplicationConfig} for all configuration values,
 * ensuring that retry limits and discount policies are consistent with other
 * services and can be changed in a single location.
 *
 * @author orvigas@gmail.com
 */
public class OrderService {
    private final Map<String, String> orders = new HashMap<>();

    /**
     * Processes an order with configuration-driven retry and discount.
     */
    public String processOrder(String userId, double amount) throws OrderException {
        for (int attempt = 0; attempt < ApplicationConfig.Order.MAX_RETRY_ATTEMPTS; attempt++) {
            try {
                Thread.sleep(attempt * ApplicationConfig.Retry.BACKOFF_MILLISECONDS);
                double discountedAmount = amount * ApplicationConfig.Order.STANDARD_DISCOUNT;
                String orderId = "ORD_" + userId + "_" + System.currentTimeMillis();
                orders.put(orderId, "PENDING");
                return orderId;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        throw new OrderException("Failed to process order after " +
            ApplicationConfig.Order.MAX_RETRY_ATTEMPTS + " attempts");
    }

    public static class OrderException extends Exception {
        public OrderException(String message) {
            super(message);
        }
    }
}

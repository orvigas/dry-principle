package com.orvigas.dry.configuration.solution;

/**
 * Centralized application configuration constants.
 *
 * <p>This class is the single source of truth for all application configuration values.
 * By centralizing configuration here, we eliminate magic numbers scattered throughout
 * services and ensure consistent policies across the application.
 *
 * <p>All constants are public and static for easy access. Changes to policies are made
 * in one place and automatically affect all services that depend on this config.
 *
 * @author orvigas@gmail.com
 */
public class ApplicationConfig {

    private ApplicationConfig() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static class User {
        private User() {
            throw new UnsupportedOperationException("Utility class");
        }

        public static final int MIN_PASSWORD_LENGTH = 8;
        public static final int MAX_PASSWORD_LENGTH = 128;
        public static final String DEFAULT_STATUS = "ACTIVE";
    }

    public static class Retry {
        private Retry() {
            throw new UnsupportedOperationException("Utility class");
        }

        public static final int MAX_ATTEMPTS = 3;
        public static final long BACKOFF_MILLISECONDS = 1000;
    }

    public static class Order {
        private Order() {
            throw new UnsupportedOperationException("Utility class");
        }

        public static final double STANDARD_DISCOUNT = 0.9;
        public static final int MAX_RETRY_ATTEMPTS = 3;
    }
}

package com.orvigas.dry.configuration.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * User service using centralized configuration.
 *
 * <p>This service eliminates hardcoded magic numbers by depending on
 * {@code ApplicationConfig} for all configuration values. Changes to
 * validation limits, status codes, or retry policies are made once
 * in {@code ApplicationConfig} and automatically affect this service.
 *
 * @author orvigas@gmail.com
 */
public class UserService {
    private final Map<String, String> users = new HashMap<>();

    /**
     * Creates a new user with configuration-driven validation limits.
     */
    public String createUser(String username, String password) throws InvalidUserException {
        if (password.length() > ApplicationConfig.User.MAX_PASSWORD_LENGTH) {
            throw new InvalidUserException("Password exceeds maximum length of " +
                ApplicationConfig.User.MAX_PASSWORD_LENGTH);
        }
        if (password.length() < ApplicationConfig.User.MIN_PASSWORD_LENGTH) {
            throw new InvalidUserException("Password must be at least " +
                ApplicationConfig.User.MIN_PASSWORD_LENGTH + " characters");
        }
        String userId = "USER_" + username;
        users.put(userId, ApplicationConfig.User.DEFAULT_STATUS);
        return userId;
    }

    /**
     * Attempts to authenticate with configuration-driven retry policy.
     */
    public boolean authenticateWithRetry(String username, String password) {
        for (int attempt = 0; attempt < ApplicationConfig.Retry.MAX_ATTEMPTS; attempt++) {
            try {
                Thread.sleep(attempt * ApplicationConfig.Retry.BACKOFF_MILLISECONDS);
                if (users.containsKey("USER_" + username)) {
                    return true;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return false;
    }

    public static class InvalidUserException extends Exception {
        public InvalidUserException(String message) {
            super(message);
        }
    }
}

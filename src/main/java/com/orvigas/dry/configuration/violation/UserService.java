package com.orvigas.dry.configuration.violation;

import java.util.HashMap;
import java.util.Map;

/**
 * User service with hardcoded configuration values (magic numbers).
 *
 * <p>This service demonstrates the DRY violation: configuration values like
 * max password length, max retry attempts, and user status codes are hardcoded
 * throughout methods. The same values are duplicated in {@code OrderService}.
 *
 * <p>Problems:
 * <ul>
 *   <li>Magic numbers lack context and intent</li>
 *   <li>Changing policies requires editing multiple files</li>
 *   <li>Values may be inconsistent across services</li>
 *   <li>Hard to test different configuration scenarios</li>
 * </ul>
 *
 * <p>See {@code com.orvigas.dry.configuration.solution.UserService} for the refactored version.
 *
 * @author orvigas@gmail.com
 */
public class UserService {
    private final Map<String, String> users = new HashMap<>();

    /**
     * Creates a new user with hardcoded validation limits and status.
     */
    public String createUser(String username, String password) throws InvalidUserException {
        if (password.length() > 128) {
            throw new InvalidUserException("Password exceeds maximum length of 128");
        }
        if (password.length() < 8) {
            throw new InvalidUserException("Password must be at least 8 characters");
        }
        String userId = "USER_" + username;
        users.put(userId, "ACTIVE");
        return userId;
    }

    /**
     * Attempts to authenticate with hardcoded retry limit and backoff.
     */
    public boolean authenticateWithRetry(String username, String password) {
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                Thread.sleep(attempt * 1000);
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

package com.orvigas.dry.validation.violation;

import java.util.regex.Pattern;

/**
 * User registration request DTO with inline email validation.
 *
 * <p>This class demonstrates the DRY violation: email validation logic is hardcoded here.
 * The same logic is duplicated in {@code EmailUpdateRequest} and would need to be updated
 * in every DTO if email rules change.
 *
 * <p>See {@code com.orvigas.dry.validation.solution.UserRegistrationRequest} for the refactored version.
 *
 * @author orvigas@gmail.com
 */
public class UserRegistrationRequest {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final int MIN_EMAIL_LENGTH = 6;
    private static final int MAX_EMAIL_LENGTH = 254;

    private String username;
    private String email;

    public UserRegistrationRequest(String username, String email) throws InvalidEmailException {
        this.username = username;
        this.email = validateEmail(email);
    }

    private String validateEmail(String email) throws InvalidEmailException {
        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("Email cannot be empty");
        }
        if (email.length() < MIN_EMAIL_LENGTH) {
            throw new InvalidEmailException("Email must be at least " + MIN_EMAIL_LENGTH + " characters");
        }
        if (email.length() > MAX_EMAIL_LENGTH) {
            throw new InvalidEmailException("Email must not exceed " + MAX_EMAIL_LENGTH + " characters");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Email format is invalid");
        }
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public static class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }
}

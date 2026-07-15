package com.orvigas.dry.validation.solution;

import java.util.regex.Pattern;

/**
 * Centralized email validator for reuse across all DTOs and services.
 *
 * <p>This class is the single source of truth for email validation rules.
 * All email validation logic is defined here, eliminating duplication across
 * {@code UserRegistrationRequest}, {@code EmailUpdateRequest}, and any future
 * DTOs that need email validation.
 *
 * <p>Changes to email validation rules happen once, in this class.
 * Tests for email validation are centralized in {@code EmailValidatorTest}.
 *
 * @author orvigas@gmail.com
 */
public class EmailValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final int MIN_EMAIL_LENGTH = 6;
    private static final int MAX_EMAIL_LENGTH = 254;

    /**
     * Validates an email address against standard rules.
     *
     * @param email the email to validate
     * @return the validated email (for chaining convenience)
     * @throws InvalidEmailException if the email is invalid
     */
    public String validate(String email) throws InvalidEmailException {
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

    public static class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }
}

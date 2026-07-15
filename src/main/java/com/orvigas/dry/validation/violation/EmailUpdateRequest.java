package com.orvigas.dry.validation.violation;

import java.util.regex.Pattern;

/**
 * Email update request DTO with inline email validation.
 *
 * <p>This class demonstrates code duplication: email validation is identical to
 * {@code UserRegistrationRequest} but repeated here. This violates DRY and creates
 * maintenance risk—if email validation rules change, this DTO might be overlooked.
 *
 * <p>See {@code com.orvigas.dry.validation.solution.EmailUpdateRequest} for the refactored version.
 *
 * @author orvigas@gmail.com
 */
public class EmailUpdateRequest {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final int MIN_EMAIL_LENGTH = 6;
    private static final int MAX_EMAIL_LENGTH = 254;

    private String userId;
    private String newEmail;

    public EmailUpdateRequest(String userId, String newEmail) throws InvalidEmailException {
        this.userId = userId;
        this.newEmail = validateEmail(newEmail);
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

    public String getUserId() {
        return userId;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public static class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }
}

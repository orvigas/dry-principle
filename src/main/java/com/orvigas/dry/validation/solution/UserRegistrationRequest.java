package com.orvigas.dry.validation.solution;

/**
 * User registration request DTO using centralized email validation.
 *
 * <p>This DTO delegates email validation to {@code EmailValidator}, eliminating
 * the duplicated validation logic found in
 * {@code com.orvigas.dry.validation.violation.UserRegistrationRequest}.
 *
 * <p>Benefits:
 * <ul>
 *   <li>No validation code duplication</li>
 *   <li>Consistent email validation across all DTOs</li>
 *   <li>Changes to email rules propagate to all DTOs automatically</li>
 *   <li>Simpler to test and maintain</li>
 * </ul>
 *
 * @author orvigas@gmail.com
 */
public class UserRegistrationRequest {
    private final EmailValidator emailValidator;
    private final String username;
    private final String email;

    public UserRegistrationRequest(String username, String email) throws EmailValidator.InvalidEmailException {
        this.emailValidator = new EmailValidator();
        this.username = username;
        this.email = emailValidator.validate(email);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}

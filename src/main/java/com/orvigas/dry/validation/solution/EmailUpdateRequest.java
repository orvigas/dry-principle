package com.orvigas.dry.validation.solution;

/**
 * Email update request DTO using centralized email validation.
 *
 * <p>This DTO delegates email validation to {@code EmailValidator}, eliminating
 * the duplicated validation logic found in
 * {@code com.orvigas.dry.validation.violation.EmailUpdateRequest}.
 *
 * <p>Both DTOs now use the same {@code EmailValidator}, ensuring consistency
 * and reducing maintenance burden.
 *
 * @author orvigas@gmail.com
 */
public class EmailUpdateRequest {
    private final EmailValidator emailValidator;
    private final String userId;
    private final String newEmail;

    public EmailUpdateRequest(String userId, String newEmail) throws EmailValidator.InvalidEmailException {
        this.emailValidator = new EmailValidator();
        this.userId = userId;
        this.newEmail = emailValidator.validate(newEmail);
    }

    public String getUserId() {
        return userId;
    }

    public String getNewEmail() {
        return newEmail;
    }
}

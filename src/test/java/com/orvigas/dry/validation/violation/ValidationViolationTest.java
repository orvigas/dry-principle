package com.orvigas.dry.validation.violation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the validation violation scenario.
 *
 * <p>This test demonstrates that the violation "works" (the DTOs compile and function),
 * but at the cost of duplicated validation logic. The key insight: the anti-pattern is
 * not a bug—it's a maintenance burden that accumulates silently.
 *
 * @author orvigas@gmail.com
 */
class ValidationViolationTest {

    @Test
    void userRegistrationValidatesEmail() throws UserRegistrationRequest.InvalidEmailException {
        UserRegistrationRequest request = new UserRegistrationRequest("john", "john@example.com");
        assertThat(request.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void userRegistrationRejectsInvalidEmail() {
        assertThatThrownBy(() -> new UserRegistrationRequest("john", "invalid"))
            .isInstanceOf(UserRegistrationRequest.InvalidEmailException.class)
            .hasMessageContaining("Email format is invalid");
    }

    @Test
    void emailUpdateValidatesEmail() throws EmailUpdateRequest.InvalidEmailException {
        EmailUpdateRequest request = new EmailUpdateRequest("user123", "newemail@example.com");
        assertThat(request.getNewEmail()).isEqualTo("newemail@example.com");
    }

    @Test
    void emailUpdateRejectsInvalidEmail() {
        assertThatThrownBy(() -> new EmailUpdateRequest("user123", "invalid"))
            .isInstanceOf(EmailUpdateRequest.InvalidEmailException.class)
            .hasMessageContaining("Email format is invalid");
    }

    @Test
    void demonstratesDuplicateValidationLogic() throws UserRegistrationRequest.InvalidEmailException, EmailUpdateRequest.InvalidEmailException {
        UserRegistrationRequest reg = new UserRegistrationRequest("john", "test@example.com");
        EmailUpdateRequest update = new EmailUpdateRequest("user1", "test@example.com");

        assertThat(reg.getEmail()).isEqualTo(update.getNewEmail());
    }
}

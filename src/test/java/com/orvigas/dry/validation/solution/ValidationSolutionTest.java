package com.orvigas.dry.validation.solution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the validation solution scenario.
 *
 * <p>This test demonstrates the benefit of extracting validation logic: tests are
 * centralized in one class ({@code EmailValidatorTest}), and all DTOs automatically
 * use consistent validation. Adding a new DTO with email validation requires no new
 * validator code—just inject and use the existing {@code EmailValidator}.
 *
 * @author orvigas@gmail.com
 */
class ValidationSolutionTest {

    @Test
    void userRegistrationUsesSharedValidator() throws EmailValidator.InvalidEmailException {
        UserRegistrationRequest request = new UserRegistrationRequest("john", "john@example.com");
        assertThat(request.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void userRegistrationRejectsInvalidEmail() {
        assertThatThrownBy(() -> new UserRegistrationRequest("john", "invalid"))
            .isInstanceOf(EmailValidator.InvalidEmailException.class)
            .hasMessageContaining("Email format is invalid");
    }

    @Test
    void emailUpdateUsesSharedValidator() throws EmailValidator.InvalidEmailException {
        EmailUpdateRequest request = new EmailUpdateRequest("user123", "newemail@example.com");
        assertThat(request.getNewEmail()).isEqualTo("newemail@example.com");
    }

    @Test
    void emailUpdateRejectsInvalidEmail() {
        assertThatThrownBy(() -> new EmailUpdateRequest("user123", "invalid"))
            .isInstanceOf(EmailValidator.InvalidEmailException.class)
            .hasMessageContaining("Email format is invalid");
    }

    @Test
    void demonstratesConsistentValidationAcrossDTOs() throws EmailValidator.InvalidEmailException {
        UserRegistrationRequest reg = new UserRegistrationRequest("john", "test@example.com");
        EmailUpdateRequest update = new EmailUpdateRequest("user1", "test@example.com");

        assertThat(reg.getEmail()).isEqualTo(update.getNewEmail());
    }
}

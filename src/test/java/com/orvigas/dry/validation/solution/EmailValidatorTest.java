package com.orvigas.dry.validation.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the centralized EmailValidator.
 *
 * <p>All email validation logic is tested in one place. Changes to validation
 * rules are verified here. New DTOs that use the validator automatically pass
 * these same tests without needing new test coverage.
 *
 * @author orvigas@gmail.com
 */
class EmailValidatorTest {
    private EmailValidator validator;

    @BeforeEach
    void setUp() {
        validator = new EmailValidator();
    }

    @Test
    void acceptsValidEmail() throws EmailValidator.InvalidEmailException {
        String email = validator.validate("user@example.com");
        assertThat(email).isEqualTo("user@example.com");
    }

    @Test
    void rejectsNullEmail() {
        assertThatThrownBy(() -> validator.validate(null))
            .isInstanceOf(EmailValidator.InvalidEmailException.class)
            .hasMessageContaining("Email cannot be empty");
    }

    @Test
    void rejectsBlankEmail() {
        assertThatThrownBy(() -> validator.validate("  "))
            .isInstanceOf(EmailValidator.InvalidEmailException.class)
            .hasMessageContaining("Email cannot be empty");
    }

    @Test
    void rejectsTooShortEmail() {
        assertThatThrownBy(() -> validator.validate("a@b.c"))
            .isInstanceOf(EmailValidator.InvalidEmailException.class)
            .hasMessageContaining("must be at least 6 characters");
    }

    @Test
    void rejectsTooLongEmail() {
        String longEmail = "a".repeat(250) + "@example.com";
        assertThatThrownBy(() -> validator.validate(longEmail))
            .isInstanceOf(EmailValidator.InvalidEmailException.class)
            .hasMessageContaining("must not exceed 254 characters");
    }

    @Test
    void rejectsEmailWithoutAtSymbol() {
        assertThatThrownBy(() -> validator.validate("userexample.com"))
            .isInstanceOf(EmailValidator.InvalidEmailException.class)
            .hasMessageContaining("Email format is invalid");
    }

    @Test
    void rejectsEmailWithoutLocalPart() {
        assertThatThrownBy(() -> validator.validate("@example.com"))
            .isInstanceOf(EmailValidator.InvalidEmailException.class)
            .hasMessageContaining("Email format is invalid");
    }

    @Test
    void acceptsEmailWithPlus() throws EmailValidator.InvalidEmailException {
        String email = validator.validate("user+tag@example.com");
        assertThat(email).isEqualTo("user+tag@example.com");
    }

    @Test
    void acceptsEmailWithDot() throws EmailValidator.InvalidEmailException {
        String email = validator.validate("first.last@example.com");
        assertThat(email).isEqualTo("first.last@example.com");
    }
}

package com.orvigas.dry.configuration.solution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the configuration solution scenario.
 *
 * <p>This test demonstrates the benefit of centralized configuration: services
 * use named constants from {@code ApplicationConfig}. Testing different policies
 * would require only changing {@code ApplicationConfig} values or using a mock/test-specific config.
 * Constants with meaningful names make policies explicit and discoverable.
 *
 * @author orvigas@gmail.com
 */
class ConfigurationSolutionTest {

    @Test
    void userServiceUsesConfiguredPasswordValidation() throws UserService.InvalidUserException {
        UserService service = new UserService();
        String userId = service.createUser("john", "SecurePass123");
        assertThat(userId).startsWith("USER_");
    }

    @Test
    void userServiceRejectsPasswordBelowConfiguredMinimum() {
        UserService service = new UserService();
        String tooShort = "a".repeat(ApplicationConfig.User.MIN_PASSWORD_LENGTH - 1);
        assertThatThrownBy(() -> service.createUser("john", tooShort))
            .isInstanceOf(UserService.InvalidUserException.class)
            .hasMessageContaining("must be at least 8 characters");
    }

    @Test
    void userServiceRejectsPasswordAboveConfiguredMaximum() {
        UserService service = new UserService();
        String tooLong = "a".repeat(ApplicationConfig.User.MAX_PASSWORD_LENGTH + 1);
        assertThatThrownBy(() -> service.createUser("john", tooLong))
            .isInstanceOf(UserService.InvalidUserException.class)
            .hasMessageContaining("exceeds maximum length of 128");
    }

    @Test
    void userServiceUsesConfiguredDefaultStatus() throws UserService.InvalidUserException {
        UserService service = new UserService();
        service.createUser("john", "SecurePass123");
        assertThat(ApplicationConfig.User.DEFAULT_STATUS).isEqualTo("ACTIVE");
    }

    @Test
    void orderServiceUsesConfiguredRetryPolicy() throws OrderService.OrderException {
        OrderService service = new OrderService();
        String orderId = service.processOrder("user1", 150);
        assertThat(orderId).startsWith("ORD_");
        assertThat(ApplicationConfig.Order.MAX_RETRY_ATTEMPTS).isEqualTo(3);
    }

    @Test
    void verifyConfigurationConstantsAreMeaningfullyNamed() {
        assertThat(ApplicationConfig.User.MIN_PASSWORD_LENGTH).isPositive();
        assertThat(ApplicationConfig.User.MAX_PASSWORD_LENGTH).isGreaterThan(ApplicationConfig.User.MIN_PASSWORD_LENGTH);
        assertThat(ApplicationConfig.Retry.MAX_ATTEMPTS).isPositive();
        assertThat(ApplicationConfig.Order.STANDARD_DISCOUNT).isGreaterThan(0).isLessThan(1);
    }
}

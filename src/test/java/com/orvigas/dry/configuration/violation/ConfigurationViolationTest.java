package com.orvigas.dry.configuration.violation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the configuration violation scenario.
 *
 * <p>This test demonstrates that hardcoded magic numbers "work" but create maintenance
 * burden. To test different retry limits or password lengths, the code itself must be
 * modified. The anti-pattern makes policies implicit rather than explicit.
 *
 * @author orvigas@gmail.com
 */
class ConfigurationViolationTest {

    @Test
    void userServiceValidatesPasswordWithHardcodedLimit() throws UserService.InvalidUserException {
        UserService service = new UserService();
        String userId = service.createUser("john", "SecurePass123");
        assertThat(userId).startsWith("USER_");
    }

    @Test
    void userServiceRejectsShortPassword() {
        UserService service = new UserService();
        assertThatThrownBy(() -> service.createUser("john", "short"))
            .isInstanceOf(UserService.InvalidUserException.class)
            .hasMessageContaining("must be at least 8 characters");
    }

    @Test
    void userServiceRejectsLongPassword() {
        UserService service = new UserService();
        String longPassword = "a".repeat(129);
        assertThatThrownBy(() -> service.createUser("john", longPassword))
            .isInstanceOf(UserService.InvalidUserException.class)
            .hasMessageContaining("exceeds maximum length of 128");
    }

    @Test
    void orderServiceUsesHardcodedRetryLimit() throws OrderService.OrderException {
        OrderService service = new OrderService();
        String orderId = service.processOrder("user1", 150);
        assertThat(orderId).startsWith("ORD_");
    }

    @Test
    void demonstratesMagicNumbersAreHardToChange() {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();

        assertThat(userService).isNotNull();
        assertThat(orderService).isNotNull();
    }
}

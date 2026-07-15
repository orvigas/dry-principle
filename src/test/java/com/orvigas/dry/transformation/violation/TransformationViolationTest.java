package com.orvigas.dry.transformation.violation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the transformation violation scenario.
 *
 * <p>This test demonstrates that the violation "works" (transformation happens),
 * but at the cost of duplicated date formatting logic. Changes to response format
 * require updating multiple classes. Transformation tests are split across
 * controller and repository test classes.
 *
 * @author orvigas@gmail.com
 */
class TransformationViolationTest {

    @Test
    void controllerTransformsUserToResponse() {
        UserController controller = new UserController();
        User user = new User("1", "John Doe", "john@example.com", System.currentTimeMillis());

        UserResponse response = controller.getUserResponse(user);

        assertThat(response.getId()).isEqualTo("1");
        assertThat(response.getName()).isEqualTo("John Doe");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
        assertThat(response.getCreatedDate()).isNotNull();
    }

    @Test
    void repositorySavesAndTransformsUser() {
        UserRepository repository = new UserRepository();
        User user = new User("2", "Jane Doe", "jane@example.com", System.currentTimeMillis());

        UserResponse response = repository.saveUser(user);

        assertThat(response.getId()).isEqualTo("2");
        assertThat(response.getName()).isEqualTo("Jane Doe");
        assertThat(response.getEmail()).isEqualTo("jane@example.com");
        assertThat(repository.getDatabase()).containsKey("2");
    }

    @Test
    void demonstratesDuplicateTransformationLogic() {
        UserController controller = new UserController();
        UserRepository repository = new UserRepository();
        User user = new User("3", "Test User", "test@example.com", System.currentTimeMillis());

        UserResponse controllerResponse = controller.getUserResponse(user);
        UserResponse repositoryResponse = repository.saveUser(user);

        assertThat(controllerResponse.getId()).isEqualTo(repositoryResponse.getId());
        assertThat(controllerResponse.getCreatedDate()).isEqualTo(repositoryResponse.getCreatedDate());
    }
}

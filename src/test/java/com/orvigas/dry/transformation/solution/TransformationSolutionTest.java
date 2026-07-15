package com.orvigas.dry.transformation.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the transformation solution scenario.
 *
 * <p>This test demonstrates the benefit of extracting transformation logic: the
 * centralized {@code UserMapper} is used by both controller and repository, ensuring
 * consistent transformations. Tests for transformation logic can focus on the mapper
 * itself, with controller and repository tests just verifying they use it correctly.
 *
 * @author orvigas@gmail.com
 */
class TransformationSolutionTest {
    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new UserMapper();
    }

    @Test
    void controllerUsesMapperForTransformation() {
        UserController controller = new UserController(mapper);
        User user = new User("1", "John Doe", "john@example.com", System.currentTimeMillis());

        UserResponse response = controller.getUserResponse(user);

        assertThat(response.getId()).isEqualTo("1");
        assertThat(response.getName()).isEqualTo("John Doe");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
        assertThat(response.getCreatedDate()).isNotNull();
    }

    @Test
    void repositorySavesAndUsesMapperForTransformation() {
        UserRepository repository = new UserRepository(mapper);
        User user = new User("2", "Jane Doe", "jane@example.com", System.currentTimeMillis());

        UserResponse response = repository.saveUser(user);

        assertThat(response.getId()).isEqualTo("2");
        assertThat(response.getName()).isEqualTo("Jane Doe");
        assertThat(response.getEmail()).isEqualTo("jane@example.com");
        assertThat(repository.getDatabase()).containsKey("2");
    }

    @Test
    void demonstratesConsistentTransformationAcrossLayers() {
        UserController controller = new UserController(mapper);
        UserRepository repository = new UserRepository(mapper);
        User user = new User("3", "Test User", "test@example.com", System.currentTimeMillis());

        UserResponse controllerResponse = controller.getUserResponse(user);
        UserResponse repositoryResponse = repository.saveUser(user);

        assertThat(controllerResponse.getId()).isEqualTo(repositoryResponse.getId());
        assertThat(controllerResponse.getCreatedDate()).isEqualTo(repositoryResponse.getCreatedDate());
    }
}

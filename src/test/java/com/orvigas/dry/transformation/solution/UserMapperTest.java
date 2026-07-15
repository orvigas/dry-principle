package com.orvigas.dry.transformation.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the centralized UserMapper.
 *
 * <p>All transformation logic is tested in one place. Changes to the response
 * format or date formatting are verified here. Controllers and repositories
 * that use the mapper automatically benefit from these tests without needing
 * to duplicate transformation test cases.
 *
 * @author orvigas@gmail.com
 */
class UserMapperTest {
    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new UserMapper();
    }

    @Test
    void mapsUserToResponse() {
        User user = new User("1", "John Doe", "john@example.com", 1609459200000L);

        UserResponse response = mapper.toResponse(user);

        assertThat(response.getId()).isEqualTo("1");
        assertThat(response.getName()).isEqualTo("John Doe");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
        assertThat(response.getCreatedDate()).isNotNull();
    }

    @Test
    void preservesAllUserFields() {
        User user = new User("123", "Alice Smith", "alice@example.com", System.currentTimeMillis());

        UserResponse response = mapper.toResponse(user);

        assertThat(response.getId()).isEqualTo(user.getId());
        assertThat(response.getName()).isEqualTo(user.getName());
        assertThat(response.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void formatsDateConsistently() {
        User user1 = new User("1", "User One", "one@example.com", 1609459200000L);
        User user2 = new User("2", "User Two", "two@example.com", 1609459200000L);

        UserResponse response1 = mapper.toResponse(user1);
        UserResponse response2 = mapper.toResponse(user2);

        assertThat(response1.getCreatedDate()).isEqualTo(response2.getCreatedDate());
    }

    @Test
    void handlesDifferentTimestamps() {
        long timestamp1 = 1609459200000L;
        long timestamp2 = 1640995200000L;

        User user1 = new User("1", "Early User", "early@example.com", timestamp1);
        User user2 = new User("2", "Late User", "late@example.com", timestamp2);

        UserResponse response1 = mapper.toResponse(user1);
        UserResponse response2 = mapper.toResponse(user2);

        assertThat(response1.getCreatedDate()).isNotEqualTo(response2.getCreatedDate());
    }
}

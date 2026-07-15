package com.orvigas.dry.transformation.violation;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * User repository with inline transformation logic.
 *
 * <p>This repository converts {@code User} domain objects to {@code UserResponse} DTOs
 * inline. The same transformation logic is duplicated in {@code UserController}.
 *
 * <p>Problems:
 * <ul>
 *   <li>Date formatting logic is repeated in two places</li>
 *   <li>If the response format changes, both classes must be updated</li>
 *   <li>Tests for transformation are split across controller and repository tests</li>
 *   <li>New classes that need to transform User to UserResponse must repeat the logic</li>
 * </ul>
 *
 * <p>See {@code com.orvigas.dry.transformation.solution.UserRepository} for the refactored version.
 *
 * @author orvigas@gmail.com
 */
public class UserRepository {
    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private final Map<String, User> database = new HashMap<>();

    /**
     * Saves a user and returns the response DTO with hardcoded transformation logic.
     */
    public UserResponse saveUser(User user) {
        database.put(user.getId(), user);
        String formattedDate = DATE_FORMATTER.format(Instant.ofEpochMilli(user.getCreatedAt()));
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), formattedDate);
    }

    public Map<String, User> getDatabase() {
        return database;
    }
}

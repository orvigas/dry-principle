package com.orvigas.dry.transformation.solution;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Centralizes all User-to-UserResponse transformation logic.
 *
 * <p>This mapper is the single source of truth for converting domain {@code User}
 * objects to API {@code UserResponse} DTOs. All date formatting and field mapping
 * logic is defined here, eliminating duplication across controllers and repositories.
 *
 * <p>Controllers, repositories, and any other callers depend on this mapper
 * to convert between layers. Changes to the response format happen once.
 *
 * @author orvigas@gmail.com
 */
public class UserMapper {
    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    /**
     * Converts a domain User to an API UserResponse.
     *
     * @param user the domain user object
     * @return the API response DTO
     */
    public UserResponse toResponse(User user) {
        String formattedDate = DATE_FORMATTER.format(Instant.ofEpochMilli(user.getCreatedAt()));
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), formattedDate);
    }
}

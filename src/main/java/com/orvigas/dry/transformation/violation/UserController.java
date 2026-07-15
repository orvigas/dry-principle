package com.orvigas.dry.transformation.violation;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * User controller with inline transformation logic.
 *
 * <p>This controller converts {@code User} domain objects to {@code UserResponse} DTOs
 * inline. The same transformation logic is duplicated in {@code UserRepository}.
 *
 * <p>See {@code com.orvigas.dry.transformation.solution.UserController} for the refactored version.
 *
 * @author orvigas@gmail.com
 */
public class UserController {
    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    /**
     * Converts a User to a UserResponse with hardcoded transformation logic.
     */
    public UserResponse getUserResponse(User user) {
        String formattedDate = DATE_FORMATTER.format(Instant.ofEpochMilli(user.getCreatedAt()));
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), formattedDate);
    }
}

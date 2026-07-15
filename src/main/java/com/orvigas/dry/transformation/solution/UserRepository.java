package com.orvigas.dry.transformation.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * User repository using centralized transformation logic.
 *
 * <p>This repository delegates all User-to-UserResponse transformation to
 * {@code UserMapper}, eliminating the duplicated transformation logic found in
 * {@code com.orvigas.dry.transformation.violation.UserRepository}.
 *
 * <p>Both the controller and repository now use the same mapper, ensuring
 * consistent response formatting across all layers.
 *
 * @author orvigas@gmail.com
 */
public class UserRepository {
    private final UserMapper userMapper;
    private final Map<String, User> database = new HashMap<>();

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Saves a user and returns the response DTO using the centralized mapper.
     */
    public UserResponse saveUser(User user) {
        database.put(user.getId(), user);
        return userMapper.toResponse(user);
    }

    public Map<String, User> getDatabase() {
        return database;
    }
}

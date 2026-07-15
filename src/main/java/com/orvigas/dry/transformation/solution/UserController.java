package com.orvigas.dry.transformation.solution;

/**
 * User controller using centralized transformation logic.
 *
 * <p>This controller delegates all User-to-UserResponse transformation to
 * {@code UserMapper}, eliminating the duplicated transformation logic found in
 * {@code com.orvigas.dry.transformation.violation.UserController}.
 *
 * <p>Benefits:
 * <ul>
 *   <li>No transformation logic duplication</li>
 *   <li>Changes to response format happen once in UserMapper</li>
 *   <li>Consistent transformation across all layers</li>
 *   <li>Simpler to test and maintain</li>
 * </ul>
 *
 * @author orvigas@gmail.com
 */
public class UserController {
    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Converts a User to a UserResponse using the centralized mapper.
     */
    public UserResponse getUserResponse(User user) {
        return userMapper.toResponse(user);
    }
}

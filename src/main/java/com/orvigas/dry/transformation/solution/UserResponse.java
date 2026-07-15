package com.orvigas.dry.transformation.solution;

/**
 * API response DTO for user data.
 *
 * @author orvigas@gmail.com
 */
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String createdDate;

    public UserResponse(String id, String name, String email, String createdDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedDate() {
        return createdDate;
    }
}

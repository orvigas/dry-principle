package com.orvigas.dry.transformation.solution;

/**
 * Domain User entity shared between layers.
 *
 * @author orvigas@gmail.com
 */
public class User {
    private String id;
    private String name;
    private String email;
    private long createdAt;

    public User(String id, String name, String email, long createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
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

    public long getCreatedAt() {
        return createdAt;
    }
}

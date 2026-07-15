/**
 * Transformation Scenario: Duplicate data mapping logic across layers.
 *
 * <h2>The Problem</h2>
 *
 * In the {@code violation} package, the {@code UserController} and {@code UserRepository}
 * each implement their own logic to convert between domain {@code User} objects and
 * API {@code UserResponse}. The same mapping (extracting fields, formatting dates, etc.)
 * is duplicated in two places. If the user data structure changes, both converters
 * need updating. Tests for mapping logic are split across controller and repository tests.
 *
 * <h2>The Solution</h2>
 *
 * In the {@code solution} package, a reusable {@code UserMapper} class handles all
 * conversion logic. Controllers and repositories now depend on this single mapper.
 * Changes to the mapping logic happen once. Tests for mapping run in one test class.
 * New callers can reuse the same mapper without reimplementing the logic.
 *
 * <h2>Key Insight</h2>
 *
 * Data transformation logic is often duplicated across API layers, persistence layers,
 * and integrations. Extracting this logic into dedicated mappers creates a clear contract
 * between layers and eliminates the maintenance burden of synchronized changes.
 */
package com.orvigas.dry.transformation;

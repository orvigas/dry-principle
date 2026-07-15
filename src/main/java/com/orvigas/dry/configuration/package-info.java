/**
 * Configuration Scenario: Magic strings and constants scattered throughout code.
 *
 * <h2>The Problem</h2>
 *
 * In the {@code violation} package, services (UserService, OrderService) contain
 * hardcoded magic strings—status codes, discount percentages, validation limits—
 * scattered throughout their methods. These "magic numbers" lack context and cannot
 * be reused. If a policy changes (e.g., increase max retry count from 3 to 5),
 * dozens of files need editing. Similar constants may be defined differently in
 * different places, leading to subtle bugs.
 *
 * <h2>The Solution</h2>
 *
 * In the {@code solution} package, a {@code ApplicationConfig} class centralizes
 * all configuration constants with meaningful names and documentation. Services
 * depend on this config class instead of hardcoding values. Changes to policies
 * happen once. The intent of each constant is clear (e.g., {@code MAX_RETRY_ATTEMPTS}
 * is more readable than the magic number 5 buried in a method).
 *
 * <h2>Key Insight</h2>
 *
 * Magic constants scattered throughout code make policies implicit and hard to change.
 * Centralized configuration makes policies explicit, discoverable, and maintainable.
 */
package com.orvigas.dry.configuration;

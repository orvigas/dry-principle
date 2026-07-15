/**
 * Configuration Scenario: Solution (DRY Applied).
 *
 * Classes in this package demonstrate the DRY principle: all configuration
 * constants are centralized in {@code ApplicationConfig} instead of scattered
 * throughout services.
 *
 * <p>Benefits:
 * <ul>
 *   <li>All configuration constants defined in one place</li>
 *   <li>Changes to policies happen once and propagate everywhere</li>
 *   <li>Constants have meaningful names, improving code readability</li>
 *   <li>Policies are discoverable and well-documented</li>
 *   <li>Tests can inject different configurations without modifying source code</li>
 * </ul>
 *
 * See {@code com.orvigas.dry.configuration.violation} for the anti-pattern.
 */
package com.orvigas.dry.configuration.solution;

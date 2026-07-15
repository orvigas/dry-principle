/**
 * Validation Scenario: Solution (DRY Applied).
 *
 * Classes in this package demonstrate the DRY principle: email validation logic
 * is extracted into a reusable {@code EmailValidator} class. DTOs now depend on
 * this validator instead of duplicating inline checks.
 *
 * <p>Benefits:
 * <ul>
 *   <li>Email validation is defined once and reused everywhere</li>
 *   <li>Changes to email rules happen in one place</li>
 *   <li>Tests for email validation run once, in {@code EmailValidatorTest}</li>
 *   <li>New DTOs automatically use consistent validation</li>
 *   <li>Easier to extend (e.g., add domain whitelisting) without touching DTOs</li>
 * </ul>
 *
 * See {@code com.orvigas.dry.validation.violation} for the anti-pattern.
 */
package com.orvigas.dry.validation.solution;

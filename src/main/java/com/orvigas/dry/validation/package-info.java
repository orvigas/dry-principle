/**
 * Validation Scenario: Duplicate validation logic across DTOs.
 *
 * <h2>The Problem</h2>
 *
 * In the {@code violation} package, multiple DTOs (UserRegistrationRequest, EmailUpdateRequest)
 * contain the same email validation logic—check format, length, and character restrictions—copied inline.
 * Changes to email validation rules require updating every DTO class.
 * Tests for email validation must be duplicated across multiple test classes.
 * New DTOs with email fields risk implementing email validation differently (inconsistent bugs).
 *
 * <h2>The Solution</h2>
 *
 * In the {@code solution} package, a reusable {@code EmailValidator} class encapsulates all email validation logic.
 * DTOs now depend on this validator, eliminating duplication.
 * Changes to email rules happen once, in one place.
 * Email validation tests live in one test class.
 * New DTOs automatically get consistent email validation by using the same validator.
 *
 * <h2>Key Insight</h2>
 *
 * DRY violations in validation create bugs that spread silently—validators changed in one class
 * are forgotten in another, leading to inconsistent behavior across the application.
 * Extraction makes the logic a single source of truth.
 */
package com.orvigas.dry.validation;

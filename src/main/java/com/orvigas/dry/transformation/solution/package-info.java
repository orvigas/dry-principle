/**
 * Transformation Scenario: Solution (DRY Applied).
 *
 * Classes in this package demonstrate the DRY principle: data transformation logic
 * is extracted into a reusable {@code UserMapper} class. Controllers, repositories,
 * and any other callers depend on this mapper instead of duplicating inline logic.
 *
 * <p>Benefits:
 * <ul>
 *   <li>Transformation logic is defined once and reused everywhere</li>
 *   <li>Changes to response format happen in one place</li>
 *   <li>Tests for transformation run once, in {@code UserMapperTest}</li>
 *   <li>New classes can reuse the same mapper without reimplementing logic</li>
 *   <li>Clear contract between domain and API layers</li>
 * </ul>
 *
 * See {@code com.orvigas.dry.transformation.violation} for the anti-pattern.
 */
package com.orvigas.dry.transformation.solution;

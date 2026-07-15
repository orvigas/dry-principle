/**
 * DRY Principle Portfolio — A comprehensive demonstration of the Don't Repeat Yourself principle.
 *
 * <h2>Overview</h2>
 *
 * This portfolio project illustrates the DRY principle through four realistic scenarios,
 * each showing a "violation" (code duplication problem) alongside its "solution" (extraction/abstraction fix).
 * The goal is to demonstrate that DRY violations have concrete, measurable costs—maintainability,
 * testability, and bug propagation—not just aesthetic complaints.
 *
 * <h2>Scenarios</h2>
 *
 * <ul>
 *   <li><strong>validation</strong>: Duplicate validation logic scattered across DTOs vs. extracted validators</li>
 *   <li><strong>configuration</strong>: Magic strings and constants scattered throughout code vs. centralized configuration</li>
 *   <li><strong>transformation</strong>: Repeated data mapping logic across domain and API layers vs. shared mappers</li>
 *   <li><strong>calculation</strong>: Business rule calculations duplicated in multiple services vs. extracted, reusable logic</li>
 * </ul>
 *
 * <h2>Package Structure</h2>
 *
 * Each scenario lives under {@code com.orvigas.dry.<scenario>} and contains:
 *
 * <ul>
 *   <li>{@code violation}: Classes showing the duplication anti-pattern</li>
 *   <li>{@code solution}: Refactored classes applying DRY extraction/abstraction</li>
 * </ul>
 *
 * <h2>Design Principles</h2>
 *
 * <ul>
 *   <li>No external infrastructure: validation, transformation, and persistence are all simulated with in-memory collections</li>
 *   <li>Plain Java: solutions focus on the DRY principle itself, not framework magic</li>
 *   <li>Testability as the metric: tests demonstrate that the DRY fix makes code easier to test, maintain, and extend</li>
 *   <li>Complete Javadoc: every class documents its role in the scenario and cross-references its counterpart</li>
 * </ul>
 */
package com.orvigas.dry;

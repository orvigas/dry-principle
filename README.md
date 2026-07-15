# DRY Principle Portfolio

A comprehensive Spring Boot 4 portfolio project demonstrating the **Don't Repeat Yourself (DRY)** principle through four realistic scenarios, each showing a code duplication anti-pattern alongside its solution.

## Overview

The DRY principle states: "Every piece of knowledge must have a single, unambiguous, authoritative representation within a system."

Code duplication violates DRY and creates concrete, measurable costs:

- **Maintenance burden**: Changes to duplicated logic must be made in multiple places
- **Inconsistency risk**: Updates can be forgotten in one location, creating silent bugs
- **Test fragmentation**: Tests for duplicated logic are split across multiple files
- **Reduced extensibility**: New code that needs the same logic must repeat it again

This portfolio demonstrates these costs and their solutions.

## Scenarios

Each scenario lives under `com.orvigas.dry.<scenario>` and contains:

- **`violation` package**: Classes showing the duplication anti-pattern
- **`solution` package**: Refactored classes applying DRY extraction/abstraction
- **Tests**: Demonstration of problem and benefit

### 1. Validation Scenario

**Problem**: Email validation logic is duplicated across multiple DTOs (`UserRegistrationRequest`, `EmailUpdateRequest`).

**Cost**: Changes to email rules require updating multiple classes. Tests for validation are split across DTOs.

**Solution**: Extract validation into a reusable `EmailValidator` class. All DTOs depend on this single validator.

**Benefit**: Email validation is defined once. Changes happen in one place. Tests are centralized.

### 2. Configuration Scenario

**Problem**: Magic strings and numbers are hardcoded throughout services (`UserService`, `OrderService`). Configuration values like max retry attempts (3), password length limits (8-128), and discount percentages (10%, 5%) are scattered throughout methods.

**Cost**: Changing a policy requires editing multiple files. Similar values may be defined differently in different places, creating subtle bugs.

**Solution**: Centralize all configuration in an `ApplicationConfig` class. Services depend on named constants with clear intent.

**Benefit**: Policies are defined once with meaningful names. Changes happen in one place. Intent is explicit, not implicit in magic numbers.

### 3. Transformation Scenario

**Problem**: Data transformation logic (converting domain objects to API responses) is duplicated in multiple layers. `UserController` and `UserRepository` each implement their own logic to convert `User` to `UserResponse`.

**Cost**: Changes to response format require updating multiple classes. Transformation tests are split across layer tests.

**Solution**: Extract transformation into a reusable `UserMapper` class. Controllers and repositories depend on this mapper.

**Benefit**: Transformation logic is defined once. Changes happen in one place. New layers can reuse the same mapper.

### 4. Calculation Scenario

**Problem**: Business rule calculations are duplicated across services. `OrderService` and `InvoiceService` each implement the same discount formula: "10% discount for orders over $100, plus 5% extra for bulk (10+ items)."

**Cost**: Changes to pricing policy require updating multiple services. Inconsistencies can accumulate silently.

**Solution**: Extract calculation into a reusable `DiscountCalculator` class. Services depend on this calculator instead of reimplementing.

**Benefit**: Discount rules are defined once. Changes happen in one place. New services automatically use correct calculations.

## Project Structure

```
src/main/java/com/orvigas/dry/
├── DryPrincipleApplication.java
├── package-info.java
├── validation/
│   ├── package-info.java
│   ├── violation/
│   │   ├── package-info.java
│   │   ├── UserRegistrationRequest.java
│   │   └── EmailUpdateRequest.java
│   └── solution/
│       ├── package-info.java
│       ├── EmailValidator.java
│       ├── UserRegistrationRequest.java
│       └── EmailUpdateRequest.java
├── configuration/
│   ├── package-info.java
│   ├── violation/
│   │   ├── package-info.java
│   │   ├── UserService.java
│   │   └── OrderService.java
│   └── solution/
│       ├── package-info.java
│       ├── ApplicationConfig.java
│       ├── UserService.java
│       └── OrderService.java
├── transformation/
│   ├── package-info.java
│   ├── violation/
│   │   ├── package-info.java
│   │   ├── User.java
│   │   ├── UserResponse.java
│   │   ├── UserController.java
│   │   └── UserRepository.java
│   └── solution/
│       ├── package-info.java
│       ├── User.java
│       ├── UserResponse.java
│       ├── UserMapper.java
│       ├── UserController.java
│       └── UserRepository.java
└── calculation/
    ├── package-info.java
    ├── violation/
    │   ├── package-info.java
    │   ├── OrderService.java
    │   └── InvoiceService.java
    └── solution/
        ├── package-info.java
        ├── DiscountCalculator.java
        ├── OrderService.java
        └── InvoiceService.java

src/test/java/com/orvigas/dry/
├── DryPrincipleApplicationTests.java
├── validation/
│   ├── violation/
│   │   └── ValidationViolationTest.java
│   └── solution/
│       ├── ValidationSolutionTest.java
│       └── EmailValidatorTest.java
├── configuration/
│   ├── violation/
│   │   └── ConfigurationViolationTest.java
│   └── solution/
│       └── ConfigurationSolutionTest.java
├── transformation/
│   ├── violation/
│   │   └── TransformationViolationTest.java
│   └── solution/
│       ├── TransformationSolutionTest.java
│       └── UserMapperTest.java
└── calculation/
    ├── violation/
    │   └── CalculationViolationTest.java
    └── solution/
        ├── CalculationSolutionTest.java
        └── DiscountCalculatorTest.java
```

## Technology Stack

- **Java**: 21 LTS
- **Spring Boot**: 4.1.0
- **Build**: Maven 3.8+
- **Testing**: JUnit 5 (Jupiter), AssertJ
- **Code Coverage**: JaCoCo 0.8.12

## Quick Start

### Prerequisites

- Java 21+
- Maven 3.8+

### Build

```bash
mvn clean package
```

### Run Tests

```bash
mvn test
```

### Run Specific Test

```bash
mvn test -Dtest=EmailValidatorTest
mvn test -Dtest=DiscountCalculatorTest#appliesToLargeOrderDiscount
```

### Generate Coverage Report

```bash
mvn clean test jacoco:report
open target/site/jacoco/index.html
```

### Run Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`. To check health:

```bash
curl http://localhost:8080/actuator/health
```

## Design Philosophy

- **No External Infrastructure**: Validation, transformation, and persistence are all simulated with in-memory collections. Focus stays on the DRY principle itself.
- **Plain Java Solutions**: Core scenarios use standard Java classes and patterns, not framework magic, so the principle itself is the focus.
- **Testability as Evidence**: Tests demonstrate that DRY extraction provides concrete benefits—easier to test, maintain, and extend.
- **Complete Documentation**: Every class includes Javadoc explaining its role in the scenario and cross-referencing its counterpart (violation ↔ solution).

## Key Takeaways

1. **Duplication has costs**: The violation packages "work," but at the cost of maintenance burden, inconsistency risk, and reduced extensibility.

2. **Extraction is the solution**: By extracting duplicated logic into reusable classes, we create a single source of truth.

3. **Single source of truth**: Changes happen once, tests are centralized, and new code automatically reuses correct logic.

4. **Intent over repetition**: Named classes and constants make intent explicit—`EmailValidator` is clearer than "validate email in multiple DTOs."

5. **DRY applies everywhere**: The principle works across validation, configuration, transformation, and business logic.

## Testing Strategy

- **Violation tests**: Demonstrate that the anti-pattern "works" but document the maintenance cost.
- **Solution tests**: Verify that the refactored approach produces consistent, testable code.
- **Centralized logic tests**: Focus on the extracted class (validator, config, mapper, calculator), ensuring thorough coverage of the shared logic.
- **Integration tests**: Verify that consumers of the extracted logic (DTOs, services, controllers) correctly use it.

## Author

Orlando Villegas (orvigas@gmail.com)

## License

This is a portfolio project for educational purposes.

# DRY Principle Project

A Spring Boot 4 project that demonstrates a practical, tested understanding of the **Don't Repeat Yourself (DRY)** principle through four scenarios, each showing a code duplication anti-pattern alongside its fix.

## Overview

DRY is usually quoted as "every piece of knowledge must have a single, unambiguous, authoritative representation within a system," which is accurate but abstract. This repository is meant to show what that means in practice: the same rule or the same value living in more than one place, and what it costs once someone has to change it.

Code duplication creates concrete, measurable costs:

- **Maintenance burden**: changes to duplicated logic must be made in multiple places
- **Inconsistency risk**: an update can be forgotten in one location, creating a silent bug
- **Test fragmentation**: tests for the same logic end up split across multiple files
- **Reduced extensibility**: new code that needs the same logic has to repeat it again

This project walks through those costs and their fixes across four scenarios: duplicated validation, duplicated configuration values, duplicated transformation logic, and duplicated business calculations.

## Scenarios

Each scenario lives under `com.orvigas.dry.<scenario>` and contains:

- **`violation` package**: classes showing the duplication anti-pattern
- **`solution` package**: the same classes refactored to extract the duplicated logic
- **Tests**: proof of the anti-pattern's cost and the fix's benefit

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

1. **Duplication has costs**: the violation packages "work," but at the cost of maintenance burden, inconsistency risk, and reduced extensibility.

2. **Extraction is the fix**: pulling duplicated logic into a reusable class creates a single source of truth.

3. **Single source of truth**: changes happen once, tests are centralized, and new code automatically reuses the correct logic.

4. **Intent over repetition**: named classes and constants make intent explicit — `EmailValidator` is clearer than "validate email in multiple DTOs."

5. **DRY applies everywhere**: the principle works across validation, configuration, transformation, and business logic.

## Testing Strategy

- **Violation tests**: prove the anti-pattern "works" — the issue is cost, not a bug.
- **Solution tests**: verify the refactored approach produces the same behavior, without the duplication.
- **Centralized logic tests**: focus on the extracted class (validator, config, mapper, calculator) to give the shared logic thorough coverage.
- **Integration tests**: verify that consumers of the extracted logic (DTOs, services, controllers) use it correctly.

## Author

Orlando Villegas (orvigas@gmail.com)

## License

This is a project for educational purposes.

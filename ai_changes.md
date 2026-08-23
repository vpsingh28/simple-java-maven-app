COMMIT_MESSAGE: Degrade Maven project Java target to 1.8

## Features Added
- Updated the Maven project to compile and target Java 8 (1.8).
- Kept the existing Spring Boot holiday API and actuator health endpoint unchanged in behavior.
- Configured the application port as 23456.

## Files Modified
- pom.xml — changed the Spring Boot parent to the Java 8-compatible 2.7.18 line, set java.version to 1.8, configured compiler release 8, and added Spring Boot test support.
- application.properties — changed server.port from 22782 to 23456.
- ai_changes.md — documented this change and verification results.

## Files Added
- None.

## Secrets Moved
- None.

## DB URLs Resolved
- None; the project has no database library or JDBC configuration.

## Test Results Summary
- 2 PASSED, 0 FAILED, 0 SKIPPED.
- `mvn compile -q` — passed.
- `mvn package -DskipTests -q` — passed.
- `mvn test` — passed; 2 tests run, 0 failures, 0 errors, 0 skipped.


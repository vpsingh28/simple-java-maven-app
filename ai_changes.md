COMMIT_MESSAGE: Add India holiday list API with pagination and Spring Boot runtime

## Features Added
- Added a Spring Boot application exposing `GET /api/v1/holidays`.
- Added a curated India holiday list with offset pagination and a maximum limit of 20.
- Added Actuator health support and configured the service port to 22782.

## Files Modified
- pom.xml — converted the sample Maven project to Spring Boot with Web and Actuator dependencies and the new application entrypoint.
- application.properties — configured port and health endpoint exposure.
- start_7a8179bebd762bf9.sh — configured deployment startup for port 22782.

## Files Added
- src/main/java/com/example/app/Application.java — Spring Boot entrypoint.
- src/main/java/com/example/app/HolidayController.java — India holiday list API.

## Secrets Moved
- None.

## DB URLs Resolved
- None; the project has no database library or JDBC configuration.

## Compilation Result
- PASSED — `mvn compile -q`, `mvn package -DskipTests -q`, and Java 17-compatible compilation completed successfully.


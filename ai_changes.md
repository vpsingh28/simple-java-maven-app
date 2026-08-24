COMMIT_MESSAGE: Convert project to Gradle and add Indian city capital APIs

## Features Added
- Added Gradle build configuration for the Spring Boot application while retaining the Maven build configuration.
- Added a paginated dummy Indian city list API at `GET /api/v1/cities` with offset pagination and a maximum limit of 20.
- Added an all-city/capital API at `GET /api/v1/cities/capitals`.
- Added a city lookup API at `GET /api/v1/cities/{cityName}`.
- Added proper `404 Not Found` JSON handling for unknown cities.
- Configured the application port as 21804 and exposed actuator health.

## Files Modified
- `pom.xml` — updated the Maven compiler target to Java 17.
- `application.properties` — configured port 21804.
- `.gitignore` — added local Gradle/tool/build artifact exclusions.
- `ai_changes.md` — documented the implementation and verification.

## Files Added
- `build.gradle` — Gradle Java/Spring Boot build configuration.
- `settings.gradle` — Gradle project name configuration.
- `src/main/java/com/example/app/City.java` — city/capital response model.
- `src/main/java/com/example/app/CityController.java` — city endpoints.
- `src/main/java/com/example/app/CityNotFoundException.java` — lookup exception.

## Secrets Moved
- None found.

## DB URLs Resolved
- None; the project has no database library or JDBC configuration.

## Compilation Result
- PASSED — `mvn compile -q`
- PASSED — `mvn package -DskipTests -q`
- Java runtime confirmed available via `java -version` (OpenJDK 21).


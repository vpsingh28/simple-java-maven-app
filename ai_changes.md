COMMIT_MESSAGE: Add JWT-authenticated username and password storage API

## Features Added
- Added `POST /api/v1/auth/register` to persist username and BCrypt-hashed password in the H2 database and return a success JSON response with HTTP 200.
- Added `POST /api/v1/auth/login` to validate credentials and return a 30-minute HS256 JWT in a success JSON response with HTTP 200.
- Added required-field validation, duplicate username handling, and invalid-login handling.

## Files Modified
- `pom.xml` — added JPA, H2, Spring Security, validation, and JWT dependencies.
- `application.properties` — configured port 27365, H2 database, JPA, and JWT settings.
- `ai_changes.md` — documented this implementation and verification.

## Files Added
- `src/main/java/com/example/app/User.java` — JPA user entity.
- `src/main/java/com/example/app/UserRepository.java` — persistence repository.
- `src/main/java/com/example/app/AuthService.java` — registration, hashing, login, and JWT generation.
- `src/main/java/com/example/app/AuthController.java` — authentication API endpoints.
- `src/main/java/com/example/app/SecurityConfig.java` — stateless security configuration.
- `src/test/java/com/example/app/service/AuthServiceTest.java` — service unit tests.
- `src/test/java/com/example/app/controller/AuthIntegrationTest.java` — API integration test.

## Secrets Moved
- JWT secret -> `app.jwt.secret` (`APP_JWT_SECRET` environment variable fallback)

## DB URLs Resolved
- None pre-existing; added local H2 database `jdbc:h2:file:./data/appdb`.

## Test Results Summary
- 5 PASSED, 0 FAILED, 0 SKIPPED (`mvn test -q`).

---



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


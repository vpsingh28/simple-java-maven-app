COMMIT_MESSAGE: Configure Maven compilation and Java enforcer for Java 17

## Features Added
- Degraded the Maven compiler release target from Java 21 to Java 17.
- Updated the Maven enforcer requirement to accept Java 17 and newer.

## Files Modified
- pom.xml — changed compiler release and required Java version from 21 to 17.

## Files Added
- None.

## Secrets Moved
- None.

## DB URLs Resolved
- None; this project has no database configuration or database dependency.

## Compilation Result
- PASSED — `mvn compile -q` and `mvn package -DskipTests -q` completed successfully.
- Environment verification: Java 21.0.12.1 is installed; the project is configured to compile for Java 17.

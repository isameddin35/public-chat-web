# ---- Build Stage ----
FROM gradle:8.10.2-jdk17 AS builder
WORKDIR /app

# Copy Gradle files first (for caching dependencies)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Download dependencies
RUN ./gradlew dependencies || return 0

# Copy the rest of the project
COPY . .

# Build the Spring Boot fat JAR
RUN ./gradlew clean bootJar -x test

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]

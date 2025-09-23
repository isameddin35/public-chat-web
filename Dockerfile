# ---- Build Stage ----
FROM gradle:8.10.2-jdk17 AS builder
WORKDIR /app

# Copy Gradle wrapper and config
COPY gradlew ./
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Make sure gradlew is executable
RUN chmod +x gradlew

# Copy the rest of the project
COPY . .

# Build Spring Boot fat JAR
RUN ./gradlew clean bootJar -x test

# Use a base image with JDK 17 and Maven
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Maven Wrapper and project files
COPY ./api-gateway/mvnw ./api-gateway/
COPY ./api-gateway/mvnw.cmd ./api-gateway/
COPY ./api-gateway/.mvn ./api-gateway/.mvn
COPY ./api-gateway/pom.xml ./api-gateway/
COPY ./api-gateway/src ./api-gateway/src

# Make the Maven Wrapper executable
RUN chmod +x ./api-gateway/mvnw

# Run the build using the Maven Wrapper
RUN ./api-gateway/mvnw clean package -DskipTests

# Create a new image for the runtime
FROM openjdk:17-jdk-slim

# Copy the built jar from the previous stage
COPY --from=build /app/api-gateway/target/*.jar /app/api-gateway.jar

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "/app/api-gateway.jar"]

# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app
RUN ls -al
# Copy your Spring Boot JAR file (assuming you have it locally)
COPY target/*.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]

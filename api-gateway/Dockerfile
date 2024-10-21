# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app
RUN find / -type f

# List files in the target directory (this will not run during the Docker build)
RUN ls -al /var/lib/jenkins/jobs/backend/branches/master/workspace

# Copy your Spring Boot JAR file (assuming you have it locally)
COPY /var/lib/jenkins/jobs/backend/branches/master/workspace/api-gateway/api-gateway-0.0.1-SNAPSHOT.war app.war

# Expose the port your application runs on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.war"]

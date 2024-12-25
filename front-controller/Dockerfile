# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
USER root
# Set the working directory
WORKDIR /app
RUN find / -type f

# List files in the target directory (this will not run during the Docker build)
RUN ls -al 
RUN chmod -R +rwx ./
RUN ./mvnw clean install

# Copy your Spring Boot JAR file (assuming you have it locally)
COPY /target/*.war app.war

# Expose the port your application runs on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.war"]

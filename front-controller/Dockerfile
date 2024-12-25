# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
USER root
# Set the working directory
WORKDIR /app

COPY / /app
RUN find / -type f
RUN pwd

# List files in the target directory (this will not run during the Docker build)
RUN ls -al 
RUN chmod -R +rwx ./
RUN ./mvnw clean install

RUN cp /target/*.war /app/app.war

# Expose the port your application runs on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.war"]

FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the pre-built JAR from the local machine into the container
COPY /api-gateway-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

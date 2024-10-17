FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY . /app

# Build the project using Maven (make sure the mvnw file is included in the repo)
RUN ./mvnw clean package -DskipTests

# Copy the JAR file from the build output
COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

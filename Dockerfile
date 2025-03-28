# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Optional: Set a volume to persist data (if required)
VOLUME /tmp

# Copy the packaged jar file into the container
# This uses an argument to generalize the jar file name
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the port your application runs on (default Spring Boot port)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]

# Use the official Maven image to create a build artifact
FROM maven:3.8.5-openjdk-21 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Use the official OpenJDK image to run the application
FROM openjdk:21-jdk-slim
COPY --from=build /home/app/target/your-app.jar /usr/local/lib/your-app.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the default command to run the jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/your-app.jar"]

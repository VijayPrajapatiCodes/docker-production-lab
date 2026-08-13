# 1. Base image
FROM eclipse-temurin:21-jre

# 2. Container ke andar working directory
WORKDIR /app

# 3. Spring Boot JAR ko container ke andar copy karo
COPY target/SpringDocker-0.0.1-SNAPSHOT.jar app.jar

# 4. Application ka port declare karo
EXPOSE 8080

# 5. Container start hone par Spring Boot application run karo
ENTRYPOINT ["java", "-jar", "app.jar"]
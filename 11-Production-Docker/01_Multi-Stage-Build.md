# 🏭 Docker Multi-Stage Build

## 1. Problem

Spring Boot application build karne ke liye Maven aur JDK ki zarurat hoti hai.

But final application ko normally sirf JRE/runtime ki zarurat hoti hai.

Agar same image mein:

    Maven
    JDK
    Source Code
    Dependencies
    JAR

sab rakh dein, image unnecessarily large ho sakti hai.

---

# 2. Multi-Stage Build

Multi-stage Dockerfile multiple FROM instructions use karta hai.

Example:

    Stage 1
    Build Application

           ↓

          JAR

           ↓

    Stage 2
    Run Application

---

# 3. Stage 1 — Build

    FROM maven:3.9-eclipse-temurin-21 AS build

    WORKDIR /app

    COPY pom.xml .

    COPY src ./src

    RUN mvn clean package -DskipTests

Stage 1 Maven + JDK use karta hai.

---

# 4. Stage 2 — Runtime

    FROM eclipse-temurin:21-jre

    WORKDIR /app

    COPY --from=build /app/target/*.jar app.jar

    EXPOSE 8081

    ENTRYPOINT ["java", "-jar", "app.jar"]

Stage 2 mein runtime image use hoti hai.

---

# 5. Complete Dockerfile

    FROM maven:3.9-eclipse-temurin-21 AS build

    WORKDIR /app

    COPY pom.xml .
    COPY src ./src

    RUN mvn clean package -DskipTests


    FROM eclipse-temurin:21-jre

    WORKDIR /app

    COPY --from=build /app/target/*.jar app.jar

    EXPOSE 8081

    ENTRYPOINT ["java", "-jar", "app.jar"]

---

# 6. Build

    docker build -t springboot-prod .

---

# 7. Run

    docker run -d \
      --name springboot-prod \
      -p 8081:8081 \
      springboot-prod

---

# 8. Advantages

- Smaller final image
- Build tools excluded
- Better separation
- Cleaner production image
- Reduced attack surface

---

# 9. Flow

    Source Code
         ↓
    Maven Build Stage
         ↓
       JAR
         ↓
    Runtime Stage
         ↓
    Final Image
         ↓
    Container

---

# 🎯 Key Points

Build stage:

    Maven + JDK

Runtime stage:

    JRE

Final image mein build tools ki zarurat nahi hoti.






FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]


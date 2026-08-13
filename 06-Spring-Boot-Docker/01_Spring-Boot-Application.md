# 🚀 Spring Boot Application — Docker

## 1. Introduction

Docker mein Spring Boot application run karne se pehle application ko normal environment mein successfully run karna chahiye.

Basic flow:

Spring Boot Source Code
↓
Maven
↓
JAR
↓
Dockerfile
↓
Docker Image
↓
Docker Container
↓
Application

---

# 2. Spring Boot Application Structure

Example:

```text
SpringDocker/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── SpringDocker/
│       │       ├── SpringDockerApplication.java
│       │       └── Controller/
│       │           └── UserController.java
│       │
│       └── resources/
│
├── target/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── Dockerfile
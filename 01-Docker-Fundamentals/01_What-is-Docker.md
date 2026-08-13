# What is Docker?

## Definition

Docker is a platform that allows us to package, distribute, and run applications inside containers.

## Why Docker?

Docker helps provide a consistent environment for applications and reduces the "works on my machine" problem.

## Basic Flow

Application
↓
Docker Image
↓
Docker Container
↓
Running Application

## Docker Image

A Docker Image is a blueprint/template used to create containers.

## Docker Container

A Docker Container is a running instance of a Docker Image.

## Docker Use Cases

- Run Spring Boot applications
- Run MySQL
- Run Redis
- Package applications
- Create consistent development environments
- Run multiple services
- Deploy applications

## Java Backend Example

Spring Boot
↓
Docker Image
↓
Docker Container
↓
Running Application



Dockerfile → Image
Compose    → Services
Network    → Communication
Volume     → Persistence
Environment → Configuration
Healthcheck → Readiness[01_What-is-Docker.md](01_What-is-Docker.md


1. Spring Boot → Docker container
2. Spring Boot → MySQL using mysql:3306
3. Spring Boot → Redis using redis:6379
4. MySQL → Volume for persistence
5. Production → Multi-stage + Healthcheck + Logs + Resource limits + Small images
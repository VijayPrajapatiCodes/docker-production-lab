# Docker Desktop

## What is Docker Desktop?

Docker Desktop is an application that provides a convenient environment for developing and running Docker containers on Windows.

## Main Components

Docker Desktop provides/integrates with:

- Docker Engine
- Docker CLI
- WSL 2 integration
- Container management tools

## Windows Architecture

Windows
↓
Docker Desktop
↓
WSL 2
↓
Docker Engine
↓
Containers# Docker Desktop

Docker Desktop is the recommended way to install and manage Docker on Windows.

It provides the tools required to develop, run, and manage Docker containers.

---

# 1. What is Docker Desktop?

Docker Desktop is a desktop application that provides a convenient environment for working with Docker on Windows.

It provides/integrates with:

- Docker Engine
- Docker CLI
- Docker Compose
- WSL 2 integration
- Docker Desktop management interface

---

# 2. Docker Desktop Architecture on Windows

A simplified architecture is:

Windows
↓
Docker Desktop
↓
WSL 2
↓
Docker Engine
↓
Containers

Docker CLI communicates with the Docker Engine.

---

# 3. What is WSL 2?

WSL stands for:

Windows Subsystem for Linux

WSL allows Linux environments to run within Windows.

Docker Desktop can use the WSL 2 backend to run Linux containers on Windows.

Simplified:

Windows
↓
WSL 2
↓
Linux Environment
↓
Docker Engine
↓
Linux Containers

---

# 4. Why Docker Uses Linux Containers?

Most Docker container workloads are Linux-based.

Docker Desktop provides the integration required to run Linux containers on Windows.

For a Java/Spring Boot developer, this allows applications and services such as:

- Spring Boot
- MySQL
- Redis
- Nginx
- Kafka

to be run using Docker on Windows.

---

# 5. Installing Docker Desktop

Download Docker Desktop from the official Docker website.

Official Website:

https://www.docker.com/products/docker-desktop/

Install Docker Desktop for Windows.

During installation, use the WSL 2 based configuration when available.

---

# 6. Start Docker Desktop

After installation:

1. Open Docker Desktop.
2. Wait until Docker Engine is running.
3. Open Command Prompt or PowerShell.
4. Run Docker commands.

Docker Desktop should be running before using the local Docker Engine.

---

# 7. Verify WSL

Open Command Prompt or PowerShell:

```bash
wsl --version

## Installation

Install Docker Desktop for Windows and use the WSL 2 based engine.

Official Website:

https://www.docker.com/products/docker-desktop/

## Important

Docker Desktop must be running before using Docker commands that communicate with the local Docker Engine.
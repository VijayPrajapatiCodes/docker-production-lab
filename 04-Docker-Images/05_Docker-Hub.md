# Docker Hub

---

# 1. What is Docker Hub?

Docker Hub is a public Docker Registry used to:

- Store images
- Download images
- Share images
- Distribute application images

Website:

https://hub.docker.com/

---

# 2. Registry

A Docker Registry is a system used to store and distribute container images.

Examples:

- Docker Hub
- Amazon Elastic Container Registry (ECR)
- GitHub Container Registry

---

# 3. Docker Hub Repository

A repository contains image references/tags.

Example:

vijay/my-spring-app

Possible tags:

vijay/my-spring-app:1.0
vijay/my-spring-app:1.1
vijay/my-spring-app:latest

---

# 4. Pull

docker pull nginx

Flow:

Docker CLI
↓
Docker Daemon
↓
Docker Hub
↓
Download image
↓
Local Docker

---

# 5. Tag

Before pushing an image, we normally tag it with the target repository name.

Example:

docker tag my-app:1.0 username/my-app:1.0

---

# 6. Login

docker login

Docker asks for authentication details.

---

# 7. Push

docker push username/my-app:1.0

Uploads the image to the specified registry repository.

---

# 8. Complete Push Flow

Build:

docker build -t my-app:1.0 .

Tag:

docker tag my-app:1.0 username/my-app:1.0

Login:

docker login

Push:

docker push username/my-app:1.0

---

# 9. Pull Someone's Image

docker pull username/my-app:1.0

---

# 10. Run Pulled Image

docker run username/my-app:1.0

---

# 11. Official Images

Docker Hub provides official images for common software such as:

- Nginx
- Redis
- MySQL
- Ubuntu

Always verify image ownership, documentation, maintenance, and security before using an image.

---

# 12. Docker Hub in Real Backend Development

A typical flow can be:

Spring Boot Application
↓
Dockerfile
↓
Docker Image
↓
Docker Hub / Private Registry
↓
AWS / Server
↓
Container

Later, AWS ECR can be used as a private registry for AWS deployments.
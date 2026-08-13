# 🏎️ Docker Image Optimization

## 1. Goal

Production image ideally should be:

- Small
- Fast
- Secure
- Simple
- Easy to maintain

---

# 2. Use Runtime Image

Spring Boot production application ko runtime-only image par run kar sakte hain.

Example:

    FROM eclipse-temurin:21-jre

Build tools final image mein required nahi hain.

---

# 3. Multi-Stage Build

Use:

    Build Stage
        ↓
      JAR
        ↓
    Runtime Stage

This keeps Maven/build tooling out of the final image.

---

# 4. .dockerignore

Create:

    .dockerignore

Example:

    .git
    .idea
    *.log
    screenshots
    README.md

If using a Dockerfile that copies a host-built JAR from `target/`, do NOT ignore `target/`.

If using a multi-stage Dockerfile that builds the JAR inside Docker, ignoring the host `target/` directory is usually appropriate.

---

# 5. Avoid COPY Everything

Avoid unnecessary:

    COPY . .

when you only need selected files for the build.

For a multi-stage Maven build:

    COPY pom.xml .
    COPY src ./src

This makes the build context cleaner.

---

# 6. Image Layers

Dockerfile instructions create image layers.

Example:

    FROM ...
    WORKDIR ...
    COPY ...
    RUN ...

Docker can cache unchanged layers.

---

# 7. Order Matters

Dependencies change less frequently than source code.

A common optimization is:

    COPY pom.xml .

before:

    COPY src ./src

This can improve build caching depending on the Maven/Dockerfile strategy.

---

# 8. Check Image Size

    docker images

or:

    docker image ls

Check:

- Image size
- Image tag
- Image ID

---

# 9. Inspect Image

    docker image inspect springboot-prod

---

# 10. Practical

Build:

    docker build -t springboot-prod .

Check:

    docker image ls

Run:

    docker run -d \
      --name springboot-prod \
      -p 8081:8081 \
      springboot-prod

Check:

    docker ps

Logs:

    docker logs springboot-prod

---

# 11. Optimization Checklist

Before production:

    ✓ Multi-stage build
    ✓ Runtime-only final image
    ✓ .dockerignore
    ✓ Avoid unnecessary files
    ✓ Use Docker layer caching
    ✓ Keep dependencies controlled
    ✓ Check image size
    ✓ Run as non-root where appropriate
    ✓ Scan images for vulnerabilities

---

# 🎯 Key Points

Small image:

    Faster pull
    Faster deployment
    Less storage
    Smaller attack surface

Optimization is not only about image size.

Security, maintainability and reproducibility are also important.

---

# `02_Instructions.md`

```markdown
# 🐳 Dockerfile Instructions

## 1. Dockerfile Instructions Kya Hoti Hain?

Dockerfile mein different instructions hoti hain jo Docker ko batati hain ki image kaise build karni hai aur container start hone par kya karna hai.

---

## 2. Important Instructions

| Instruction | Purpose |
|---|---|
| FROM | Base image |
| WORKDIR | Working directory |
| COPY | Files copy karna |
| RUN | Build-time command |
| EXPOSE | Port declaration |
| ENV | Environment variable |
| CMD | Default command |
| ENTRYPOINT | Main executable |
| ARG | Build-time variable |
| USER | Container user |
| LABEL | Metadata |
| HEALTHCHECK | Health check |

---

## 3. Basic Structure

```dockerfile
FROM nginx:latest

WORKDIR /app

COPY index.html .

RUN echo "Building image"

ENV APP_NAME=MyApp

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"] 
# Docker Image Tags

---

# 1. What is a Tag?

A tag is a human-readable reference used to identify a particular image version or variant.

Example:

redis:7

redis = image/repository

7 = tag

---

# 2. Examples

nginx:1.31

redis:7

mysql:8.0

ubuntu:24.04

---

# 3. Latest

nginx:latest

`latest` is simply a tag.

It does NOT technically guarantee that the image is the newest software release.

---

# 4. Pull Specific Tag

docker pull redis:7

---

# 5. Run Specific Tag

docker run redis:7

---

# 6. List Tags Locally

docker image ls

Example:

REPOSITORY   TAG
redis        7
redis        latest

---

# 7. Create Your Own Tag

docker tag nginx my-nginx:1.0

Now:

docker image ls

may show:

nginx       latest
my-nginx    1.0

The tag is another reference to the image.

---

# 8. Registry Image Naming

Example:

docker.io/library/nginx:latest

Structure:

docker.io
↓
Registry

library
↓
Namespace

nginx
↓
Repository

latest
↓
Tag

---

# 9. Application Image Example

Suppose our Spring Boot application is:

vijay/spring-api

Tags:

vijay/spring-api:1.0
vijay/spring-api:1.1
vijay/spring-api:2.0

Each tag can refer to a specific image/version.

---

# 10. Production Recommendation

Avoid blindly depending on:

latest

Prefer explicit and controlled versions/tags where reproducibility matters.

---

# Practical

docker pull nginx

docker tag nginx my-nginx:1.0

docker image ls

Then remove only the tag:

docker image rm my-nginx:1.0

The underlying image may still exist through another reference.
# Pulling Docker Images

---

# 1. What is docker pull?

`docker pull` downloads an image from a Docker Registry.

The default registry commonly used is Docker Hub.

---

# 2. Pull Nginx

docker pull nginx

---

# 3. Pull Redis

docker pull redis

---

# 4. Pull Specific Version

docker pull redis:7

Here:

redis = repository/image

7 = tag

---

# 5. Pull MySQL

docker pull mysql:8.0

---

# 6. Check Images

docker image ls

or:

docker images

---

# 7. Pull Process

docker pull nginx

Flow:

Docker CLI
↓
Docker Daemon
↓
Registry
↓
Check image
↓
Download layers
↓
Store locally
↓
Image available

---

# 8. What if Image Already Exists?

If the requested image/tag is already available locally and up to date, Docker may not need to download the image layers again.

---

# 9. Practical

docker pull redis:7

Then:

docker image ls

Then:

docker image inspect redis:7

---

# Important

`docker run` can automatically pull a required image if it is not already available locally.
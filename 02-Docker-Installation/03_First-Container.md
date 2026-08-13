
---

# `03_First-Container.md`

```md
# First Docker Container

In this chapter, we will create and manage our first real running Docker container.

We will use Nginx as the example application.

---

# 1. Why Nginx?

Nginx is a web server and reverse proxy.

It is a good Docker learning example because:

- It continuously runs as a server.
- It listens on a network port.
- We can access it from a browser.
- We can start and stop its container.
- We can view its logs.
- We can execute commands inside its container.
- We can modify its web files.

---

# 2. Check Existing Images

Before running Nginx, check local images:

```bash
docker image ls
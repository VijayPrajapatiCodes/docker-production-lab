# 📋 Docker Logs

## 1. What are Docker Logs?

Docker captures the standard output and error output of containers.

Logs are useful for:

- Debugging
- Monitoring
- Application errors
- Startup problems
- Database errors

---

# 2. View Logs

    docker logs container-name

Example:

    docker logs springboot-app

---

# 3. Follow Logs

    docker logs -f springboot-app

`-f` means follow.

Logs continuously appear as the application writes them.

---

# 4. Last N Lines

    docker logs --tail 100 springboot-app

Shows the last 100 lines.

---

# 5. Logs Since a Time

    docker logs --since 10m springboot-app

Shows recent logs.

---

# 6. Compose Logs

    docker compose logs app

MySQL:

    docker compose logs mysql

Redis:

    docker compose logs redis

---

# 7. Follow Compose Logs

    docker compose logs -f app

Multiple services:

    docker compose logs -f

---

# 8. Practical

Start:

    docker compose up -d

Check:

    docker compose ps

Application logs:

    docker compose logs app

Follow:

    docker compose logs -f app

Stop following:

    CTRL + C

---

# 9. Important

`CTRL + C` while using:

    docker compose logs -f

normally stops following the output; it does not by itself remove the containers.

---

# 🎯 Key Points

    docker logs

works with a container.

    docker compose logs

works with Compose services.

Use `-f` for live logs.
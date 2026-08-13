# Docker Logs

---

# 1. What are Docker Logs?

Docker logs allow us to view output produced by the main process of a container.

For many applications, this includes:

- Startup messages
- Errors
- Requests
- Warnings
- Application output

---

# 2. docker logs

## Command

docker logs my-nginx

Displays available captured logs.

---

# 3. Follow Logs

## Command

docker logs -f my-nginx

`-f` means follow.

New log entries are displayed as they appear.

Stop following:

Ctrl + C

Important:

Ctrl + C here stops following the log stream.
It does not normally stop the container.

---

# 4. Last N Lines

docker logs --tail 50 my-nginx

Shows the last 50 lines.

---

# 5. Logs Since a Time

docker logs --since 10m my-nginx

Shows logs generated since the specified time.

---

# 6. Logs Until a Time

docker logs --until 5m my-nginx

Can be used with Docker's supported time formats to limit the log range.

---

# 7. Timestamps

docker logs -t my-nginx

Adds timestamps to log output.

---

# 8. Combine Options

docker logs -f --tail 50 my-nginx

Starts with the last 50 lines and continues following new logs.

---

# 9. Practical

Start Nginx:

docker run -d --name my-nginx -p 8080:80 nginx

View logs:

docker logs my-nginx

Follow logs:

docker logs -f my-nginx

Open:

http://localhost:8080

Refresh the browser.

Nginx access requests may appear in the log output.

---

# 10. Important Concept

Docker logs mainly show the container's captured STDOUT and STDERR.

Not every log file stored inside the container filesystem is automatically displayed by:

docker logs

---

# Interview Questions

Q: What does docker logs do?

It displays output captured from the container's main process.

Q: What does -f mean?

Follow the log stream continuously.

Q: Does Ctrl+C with docker logs -f stop the container?

No. It normally stops following the log stream.
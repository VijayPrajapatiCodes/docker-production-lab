# Docker Exec

---

# 1. What is docker exec?

`docker exec` runs a command inside an already running container.

It is extremely useful for:

- Debugging
- Inspecting files
- Checking configuration
- Running shell commands
- Troubleshooting applications

---

# 2. Syntax

docker exec [OPTIONS] CONTAINER COMMAND

---

# 3. Open Bash

docker exec -it my-nginx bash

---

# 4. Open SH

docker exec -it my-nginx sh

Some lightweight images may not contain Bash.

In that case use:

sh

---

# 5. Meaning of -it

-i

Interactive mode.

Keeps STDIN open.

-t

Allocates a pseudo-terminal.

Together:

-it

provides an interactive terminal session.

---

# 6. Run a Single Command

docker exec my-nginx ls

Example:

docker exec my-nginx ls /usr/share/nginx/html

---

# 7. Check Current Directory

docker exec my-nginx pwd

---

# 8. Check Hostname

docker exec my-nginx hostname

---

# 9. Interactive Practical

docker exec -it my-nginx bash

Inside container:

ls

pwd

hostname

ls /usr/share/nginx/html

Exit:

exit

---

# 10. Important

docker exec requires the container to be running.

It does not create a new container.

It creates a new process inside the existing container.

---

# Interview Question

Q: What is the difference between docker exec and docker run?

docker run creates a new container.

docker exec executes a command inside an existing running container.
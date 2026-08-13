# Docker Container Lifecycle

---

# 1. Lifecycle

A Docker container can move through several states.

Basic flow:

Docker Image
↓
Create
↓
Created
↓
Start
↓
Running
↓
Stop
↓
Exited
↓
Remove
↓
Deleted

---

# 2. Create Only

docker create --name my-nginx nginx

This creates a container but does not start it.

Check:

docker ps -a

You should see:

Created

---

# 3. Start

docker start my-nginx

Now the existing container starts.

---

# 4. Run

docker run --name my-nginx nginx

`docker run` combines creation and starting of a new container.

Conceptually:

docker create
+
docker start

---

# 5. Stop

docker stop my-nginx

Container becomes stopped/exited.

---

# 6. Restart

docker restart my-nginx

Equivalent conceptually to:

docker stop
↓
docker start

---

# 7. Remove

docker rm my-nginx

Removes the container.

The image remains separate.

---

# 8. Force Remove

docker rm -f my-nginx

Can stop and remove a running container.

---

# 9. Check State

docker ps

Running containers.

docker ps -a

All containers.

---

# 10. Practical Lifecycle

Create:

docker create --name lifecycle-nginx nginx

Check:

docker ps -a

Start:

docker start lifecycle-nginx

Check:

docker ps

Stop:

docker stop lifecycle-nginx

Check:

docker ps -a

Remove:

docker rm lifecycle-nginx

Check:

docker ps -a

---

# 11. Important States

Created
Running
Exited
Restarting
Paused
Dead

The exact state depends on what the container is doing and how it was configured.

---

# 12. Important Difference

Container stopped:

Container still exists.

Container removed:

Container no longer exists.

Image removed:

Image no longer exists locally.

These are separate operations.
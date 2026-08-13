# Docker Image Layers

Image layers are one of the most important Docker concepts.

---

# 1. What is a Layer?

A Docker Image is built from multiple filesystem layers.

Conceptually:

Image
│
├── Layer 1
├── Layer 2
├── Layer 3
└── Layer 4

Each layer represents filesystem changes.

---

# 2. Why Layers?

Layers provide:

- Reuse
- Caching
- Efficient storage
- Faster builds
- Faster transfers when common layers already exist

---

# 3. Example

Imagine an image has:

Layer 1
Ubuntu base files

Layer 2
Java runtime

Layer 3
Application dependencies

Layer 4
Application files

Final image:

Layer 4
Layer 3
Layer 2
Layer 1

---

# 4. Shared Layers

Two images may share layers.

Example:

Image A:

Layer 1
Layer 2
Layer 3

Image B:

Layer 1
Layer 2
Layer 4

Layer 1 and Layer 2 can be shared.

This saves storage and can improve transfer efficiency.

---

# 5. Image Layers are Immutable

Image layers are read-only/immutable.

When a container runs, Docker adds a writable layer above the image layers.

Architecture:

Writable Container Layer
↓
Image Layer 3
↓
Image Layer 2
↓
Image Layer 1

---

# 6. Container Changes

If we modify a file inside a running container:

echo "Hello" > /some/file

The change happens in the container's writable layer.

It does NOT automatically change the original image.

---

# 7. Practical

Run:

docker run -d --name layer-nginx -p 8081:80 nginx

Enter:

docker exec -it layer-nginx bash

Modify:

echo "Hello Docker" > /usr/share/nginx/html/index.html

Exit:

exit

Check browser:

http://localhost:8081

The container shows the modified file.

---

# 8. Delete Container

docker stop layer-nginx

docker rm layer-nginx

Create a new container:

docker run -d --name layer-nginx-new -p 8081:80 nginx

The original nginx image is unchanged.

The previous container's modification is not automatically part of the image.

---

# 9. View Layers

docker image history nginx

This shows image history and layer-related information.

---

# 10. Important

Image layers and container writable layers are different.

Image:

Read-only layers

Container:

Image layers
+
Writable container layer

---

# Interview Question

Q: Why does Docker use layers?

Layers allow Docker to reuse unchanged filesystem content, reducing storage and improving build/pull efficiency.
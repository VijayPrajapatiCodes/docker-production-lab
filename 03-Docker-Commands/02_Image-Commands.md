# Docker Image Commands

Docker Images are templates used to create Docker Containers.

---

# 1. What is a Docker Image?

A Docker Image is a read-only template containing:

- Application files
- Dependencies
- Configuration
- Metadata
- Filesystem layers

Example:

nginx:latest

This image can be used to create containers.

---

# 2. docker images

Lists local Docker Images.

## Command

docker images

Example:

docker images

---

# 3. docker image ls

Modern command for listing images.

docker image ls

It provides information such as:

- Repository
- Tag
- Image ID
- Created
- Size

Example:

REPOSITORY    TAG       IMAGE ID
nginx         latest    ...

---

# 4. docker pull

Downloads an image from a Docker Registry.

## Command

docker pull nginx

## Specific Version

docker pull nginx:1.31

## Redis

docker pull redis:7

## MySQL

docker pull mysql:8.0

---

# 5. docker image inspect

Shows detailed information about an image.

## Command

docker image inspect nginx

Information may include:

- Image ID
- Architecture
- Operating system
- Environment variables
- Entrypoint
- CMD
- Exposed ports
- Root filesystem
- Layers

Output is JSON.

---

# 6. docker image history

Shows the history/layers of an image.

## Command

docker image history nginx

It can show:

- Layer IDs
- Created time
- Commands
- Layer sizes

This is useful for understanding how an image was constructed.

---

# 7. docker image rm

Removes an image.

## Command

docker image rm nginx

Short form:

docker rmi nginx

The image generally cannot be removed while containers still depend on it.

---

# 8. docker image prune

Removes unused image data according to Docker's pruning rules.

## Command

docker image prune

Docker asks for confirmation.

Use carefully.

For more aggressive cleanup:

docker image prune -a

This can remove unused images that are not referenced by existing containers.

---

# 9. docker image tag

Creates another tag/reference for an existing image.

## Syntax

docker image tag SOURCE_IMAGE TARGET_IMAGE

## Example

docker image tag nginx my-nginx:1.0

Now both references can point to the same underlying image content.

---

# 10. docker image build

Builds an image from a Dockerfile.

## Syntax

docker image build -t IMAGE_NAME:TAG PATH

Example:

docker image build -t my-app:1.0 .

The `.` means the current directory is used as the build context.

Dockerfile will be used during the build.

---

# 11. docker push

Uploads an image to a registry.

Example:

docker push username/my-app:1.0

Usually:

1. Build image
2. Tag image
3. Login to registry
4. Push image

---

# 12. docker save

Saves an image to a tar archive.

Example:

docker save -o nginx.tar nginx

This is useful for transferring image data without pulling from a registry.

---

# 13. docker load

Loads an image from a tar archive.

Example:

docker load -i nginx.tar

---

# 14. Practical

Check images:

docker images

Pull Redis:

docker pull redis:7

Check:

docker images

Inspect:

docker image inspect redis:7

View history:

docker image history redis:7

---

# Important

Image and Container are different objects.

Image:

nginx:latest

Container:

my-nginx

Removing:

docker rm my-nginx

does not automatically remove:

nginx:latest
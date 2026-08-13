# Docker Hub

## What is Docker Hub?

Docker Hub is a public Docker Registry used to store, distribute, and share Docker Images.

## Docker Registry

A Docker Registry is a storage and distribution system for Docker Images.

Examples:

- Docker Hub
- Amazon ECR
- GitHub Container Registry

## Docker Pull

The `docker pull` command downloads an image from a registry.

Example:

docker pull redis

Flow:

Docker Hub
↓
Redis Image
↓
Local Docker

## Docker Run

The `docker run` command creates and starts a container from an image.

Example:

docker run redis

Flow:

Redis Image
↓
docker run
↓
Redis Container
↓
Redis Running

## Docker Repository

A repository organizes Docker Images and their tags.

Example:

redis

## Docker Image Tag

A tag identifies a particular version or variant of an image.

Example:

redis:7

Here:

redis = image/repository name
7 = tag

## Latest Tag

`latest` is a tag, not a guarantee that the image represents the newest software version.

Example:

redis:latest

## Image Reference

Example:

docker.io/library/redis:7

docker.io = registry
library = namespace
redis = repository
7 = tag

## Push Image

Images can be uploaded to a registry using:

docker push username/image-name:tag

## Important Commands

docker pull redis
docker images
docker image ls
docker run redis
docker push username/image-name:tag
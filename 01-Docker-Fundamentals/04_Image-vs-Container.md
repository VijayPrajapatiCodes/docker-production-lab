# Docker Image vs Container

## Docker Image

A Docker Image is a read-only template used to create Docker Containers.

An image contains the application, required dependencies, files, and configuration needed to run the application.

Examples:

- redis
- mysql
- nginx
- ubuntu

## Docker Container

A Docker Container is an isolated running environment created from a Docker Image.

## Basic Flow

Docker Image
↓
Docker Container
↓
Running Application

## Real-Life Analogy

Blueprint
↓
House

Docker:

Image
↓
Container

## One Image, Multiple Containers

A single Docker Image can be used to create multiple containers.

Docker Image
↓
├── Container 1
├── Container 2
└── Container 3

## Image vs Container

| Image | Container |
|---|---|
| Blueprint/template | Running instance |
| Read-only template | Has a writable container layer |
| Used to create containers | Created from an image |
| Can create multiple containers | Represents an individual container |

## Important Commands

Run a container:

docker run redis

Stop a container:

docker stop <container-name>

Remove a container:

docker rm <container-name>

Remove an image:

docker rmi <image-name>

## Important Concept

Deleting a container does not automatically delete its image.

One image can be used to create multiple containers.
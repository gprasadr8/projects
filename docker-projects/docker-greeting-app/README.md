# Docker Greet Java Project

- First compile java file
```shell
 javac DockerGreetApp.java
```

- Create `Dockerfile` with below instructions
```dockerfile
FROM openjdk:11

RUN mkdir /app

COPY DockerGreetApp.class /app

WORKDIR /app

CMD java DockerGreetApp
```

- Build an image from a Dockerfile.
- Go to dir where we have Dockerfile and run below cmd
```shell
 docker build -t my_docker_greet:1.0
```
- validate if the image is built successfully or not by using `docker images` command

- Now we successfully built the image let's run the docker image in an interactive mode with attached terminal

```shell
# -i for interactive -t to display container terminal
docker run -it my_docker_greet:1.0 
```

- it asks for `Enter Your Name:` once you enter your name it will show `Hello Name!`
- Without entering the name if you run `docker ps` command then you can see this as a running container




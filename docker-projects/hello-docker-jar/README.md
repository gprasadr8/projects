# Hello Docker from Jar file Java Project

- First build gradle project to create jar file
- Goto dir where `build.gradle` file located and run below cmd

```shell
$ ./gradlew clean build
```

- verify `/build/libs/hello-docker.jar` jar file is creted or not
- once the jar file is created then: Create `Dockerfile` with below instructions

```dockerfile
#Dockerfile
# using openjdk 11 as the base image for this application
FROM openjdk:11

# create a new app directory for my application files
# app dirctory will be created on container
RUN mkdir /app

# copy all the source files from the host machine to docker image file system
COPY /build/libs/hello-docker.jar /app

# set the workdir for executing the future commands means set workdir to image file system
WORKDIR /app

# Run the main class
CMD java -jar hello-docker.jar
```

- Build an image from a Dockerfile.
- Go to dir where we have Dockerfile and run below cmd
```shell

 docker build -t hello-docker-jar:1.0 .
```
- validate if the image is built successfully or not by using `docker images` command

- Now we successfully built the image let's run the docker image in an interactive mode with attached terminal

```shell
# -i for interactive -t to display container terminal
docker run hello-docker-jar:1.0 
```

- It executes jar file and display: `Hello Docker!`




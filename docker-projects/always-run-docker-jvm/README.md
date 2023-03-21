# Hello Docker Java Project

- First compile java file
```shell
javac HelloDockerSleep.java
```

- Create `Dockerfile` with below instructions
```dockerfile
# use openjdk:11 as the base image
FROM openjdk:11
# create app dir on image file system
RUN mkdir /app

#copy compiled files to app dir
COPY HelloDockerSleep.class /app/HelloDockerSleep.class

# set app dir is the working dir after this all the commands will be run from this app dir
WORKDIR /app

# run java main class
CMD java HelloDockerSleep
```

- Build an image from a Dockerfile.
- Go to dir where we have Dockerfile and run below cmd

```shell
# image with only name
docker build -t my_hello_docker_sleep .
```

- Now we successfully built the image let's run the docker image

```shell
docker run my_hello_docker_sleep
```
- it executes the main method and prints: `Hello Docker!`
- Every 5 seconds it displays: `HelloDockerSleep running......`

- To stop the container run `docker ps` command and get the container id then run below command
```shell
docker stop container_id
```





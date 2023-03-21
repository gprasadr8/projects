# Hello Docker Java Project

- First compile java file
```shell
javac HelloDocker.java
```

- Create `Dockerfile` with below instructions
```dockerfile
# use openjdk:11 as the base image
FROM openjdk:11
# create app dir on image file system
RUN mkdir /app

#copy compiled files to app dir
COPY HelloDocker.class /app/HelloDocker.class

# set app dir is the working dir after this all the commands will be run from this app dir
WORKDIR /app

# run java main class
CMD java HelloDocker
```

- Build an image from a Dockerfile.
- Go to dir where we have Dockerfile and run below cmd
```shell
docker build . # this will create an image but without any name
# dot(.) represents current dir where Dockerfile located
docker images
 
 # result:
 REPOSITORY        TAG       IMAGE ID       CREATED          SIZE
 #<none>          <none>    b6f4c319a35d   9 minutes ago    654MB
```

- to give a name to building image run below command:

```shell
# image with only name
docker build -t my_hello_docker .
# build image with name:version
docker build -t my_hello_docker:1.0 . 
docker images
#result:
#REPOSITORY        TAG       IMAGE ID       CREATED          SIZE
#my_hello_docker   latest    b6f4c319a35d   13 minutes ago   654MB
```
- Now we successfully built the image let's run the docker image

```shell
docker run my_hello_docker
#run with version
docker run my_hello_docker:1.0
```
- it executes the main method and prints: `Hello Docker!`




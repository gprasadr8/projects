# using openjdk 11 as the base image for this application
FROM openjdk:11

# create a new app directory for my application files
# app dirctory will be created on container
RUN mkdir /app

# copy jar file from the host machine to docker image file system
COPY /build/libs/hello-docker.jar /app

# set the workdir for executing the future commands means set workdir to image file system
WORKDIR /app

# Run the main class
CMD java -jar hello-docker.jar


# using openjdk 11 as the base image for this application
FROM openjdk:11

# create a new app directory for my application files
# app dirctory will be created on container
RUN mkdir /app

# copy all the source files from the host machine to docker image file system
COPY /build/classes/java/main/com/dg/HelloWorldDocker /app

# set the workdir for executing the future commands means set workdir to image file system
WORKDIR /app

# Run the main class
CMD java HelloWorldDocker


Startup Script:

#!/bin/bash
sudo apt-get update
sudo apt-get -y install openjdk-11-jdk
mkdir app
cd app
wget https://raw.githubusercontent.com/gprasadr8/projects/main/gcp-demo-jars/bms-rest-1.0.0.jar
java -jar -Dserver.port=8080 bms-rest-1.0.0.jar

if you want to use another port then we need to enable TCP port through firewall policies


Resources: 
https://www.knowledgefactory.net/2023/02/deploying-spring-boot-application-in-google-cloud-compute-engine-vm.html
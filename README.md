Git Commands

Step 1: 
git init
// This creates a hidden .git directory to track changes
Step 2:
git add .
//  The . command adds all files in the current directory to the staging area. You can use a .gitignore file to exclude specific files (like sensitive data or build artifacts).
Step 3:
git commit -m "Initial commit"
// Commit your staged files to your local repository:
// The -m flag allows you to provide a descriptive message for your commit.
Step 4:
git remote add origin [remote_repository_url]
// Link your local repository to the remote repository you created online:
// Replace [remote_repository_url] with the URL you copied earlier. origin is the default name given to the remote server.

Step 5:
// Push your code to the remote repository
git push -u origin main
// git push uploads your local commits to the remote repository.
// The -u flag sets the remote origin as the upstream branch. This allows you to use git push and git pull without specifying the remote and branch name in the future.
// The default branch name may be main or master depending on your setup; use the correct name. 

For doing new work
Step 1:
git checkout -b <branch-name>

git status  
-- to get the chnged files

git add .

git commit -m "testbranch changes commit to local"

git push --set-upstream origin testbranch

remote: Create a pull request for 'testbranch' on GitHub by visiting:
remote:      https://github.com/shbrkp/ecom-application/pull/new/testbranch


Actuator (changes reflected in properties and xml)
info :  http://localhost:8080/actuator/info
Shutdown : http://localhost:8080/actuator/info

Docker and Docker hub
Create an  image our application and post in Docker hub
Create an  image our application
Step 1: Creating an image of our application
Step 1.1: Open Docker Desktop. Please note, this should be up and running before executing a command
Step 1.2: Open Intellij terminal & goto the root directory of our application and put the below command

./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=<docker-login-name>/<application-name"

Sample

./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=shbrkp/ecom-application"

here the : shbrkp - docker username
ecom-application - application name which is presented in Docker

U can seee some logs after executing above command 

Step 1.3: Now the application will be available in Docker Desktop under the 'image' section. Please check
Step 2: Push this image to Docker Hub
Step 2.1: login to the Docker from the same terminal by using the below command

docker login

Step 2.2: after the login, give the below command to push to docker hub

docker push <docker-login-name>/<application-name>
Sample : docker push shbrkp/ecom-application

Step 2.3: After the process, access the docker hub from browser and u can see the image from the repositories section



Running our app from Docker Desktop (Docker desktop should be up and running)
Step 1: Important commands

docker images

docker images which are available in Docker desktop. We need to pull the image from the Docker 
Hub if we need an image which are not available in the Docker Desktop.

docker ps 

currently running processes in Docker Desktop

docker run -d -p 8080:8080 <docker-login-name>/<application-name>
sample : docker run -d -p 8080:8080 shbrkp/ecom-application

To run a java image from Docker Desktop. After this the command the tomcat will be started and running

TO get logs
docker logs <container-id>

To stop a image
docker stop <container-id>

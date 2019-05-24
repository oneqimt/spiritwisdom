WORK FLOW

edit html files in bootstrap-client
then follow build process below


BUILD bootstrap project using gulp
 cd bootstrap-client
 gulp build

 cd ../ go back to project root

 RUN GRADLE TASK - this will copy the files from dist directory to webapp directory
 gradle copyTask

 BUILD ARTIFACTS - WAR for server
 Build --> Build Artifacts --> Build All

 OR FROM Terminal

 gradle tasks

 NOTE : we may be able to automate this further using gradle






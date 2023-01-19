FROM amazoncorretto:11-alpine-jdk 
MAINTAINER josebackend 
COPY target/josebackend-0.0.1-SNAPSHOT.jar josebackend-0.0.1-SNAPSHOT.jar 
ENTRYPOINT ["java","-jar","/josebackend-0.0.1-SNAPSHOT.jar"]

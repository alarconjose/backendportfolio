FROM amazoncorretto:11-alpine-jdk
MAINTAINER JOSEBACKEND
COPY target/josebackend-0.0.1-SNAPSHOT.jar josebackend-app.jar
ENTRYPOINT ["java","-jar","/josebackend-app.jar"]

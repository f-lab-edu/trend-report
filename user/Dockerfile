FROM openjdk:21
WORKDIR /app
COPY ./build/libs/user-0.0.1-SNAPSHOT.jar user.jar
ENTRYPOINT ["java","-jar","user.jar"]
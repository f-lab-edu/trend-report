FROM openjdk:21
WORKDIR /app
COPY ./build/libs/external-0.0.1-SNAPSHOT.jar external.jar
ENTRYPOINT ["java","-jar","external.jar"]
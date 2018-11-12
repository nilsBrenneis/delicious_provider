FROM openjdk:8-jre-alpine

WORKDIR /tmp

VOLUME /tmp

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]
FROM amazoncorretto:17-alpine-jdk
RUN mkdir /opt/app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /opt/app/app.jar

WORKDIR /opt/app
ENTRYPOINT ["java","-jar","app.jar"]
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} finted-backend.jar
ENTRYPOINT ["java","-jar","/finted-backend.jar"]

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY /target/finted-backend.jar finted-backend.jar
ENTRYPOINT ["java","-jar","/finted-backend.jar"]
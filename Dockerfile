FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY . /app
RUN mvn clean package


FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/finted-backend.jar /app
CMD ["java", "-jar", "finted-backend.jar"]

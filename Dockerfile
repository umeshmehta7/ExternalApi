FROM gradle:7.5.1-jdk17 AS build

WORKDIR /app

COPY gradlew gradlew.bat gradle/ ./

COPY . .

RUN ./gradlew bootJar

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar","app.jar"]
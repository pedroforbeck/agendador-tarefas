FROM gradle:7.6-eclipse-temurin-17 AS BUILD
WORKDIR /app
COPY . .
run gradle build --no-daemon
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/buid/libs/*.jar /app/agendador-tarefas.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/agendador-tarefas.jar"]
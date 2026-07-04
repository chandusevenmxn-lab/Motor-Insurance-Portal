# Multi-stage build for Spring Boot backend
FROM gradle:8.4-jdk21 AS builder
WORKDIR /workspace
COPY . /workspace
RUN ./gradlew :backend:experience-api:bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
# copy built jar (assumes single jar in build/libs)
COPY --from=builder /workspace/backend/experience-api/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]

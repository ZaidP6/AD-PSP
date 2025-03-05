FROM bellsoft/liberica-openjdk-alpine:17 AS build
WORKDIR /workspace/vizitable-app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw install -DskipTests

FROM bellsoft/liberica-openjdk-alpine:17

#ENV SPRING_DATASOURCE_URL=
#ENV SPRING_DATASOURCE_USERNAME=
#ENV SPRING_DATASOURCE_PASSWORD=
#ENV JWT_SECRET=

WORKDIR /vizitable-app
COPY --from=build /workspace/vizitable-app/target/*.jar /app/app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
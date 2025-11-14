FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY service-common/pom.xml ./service-common/
COPY service-solicitudes/pom.xml ./service-solicitudes/
# copy sources to build only this module to produce jar faster
COPY service-solicitudes ./service-solicitudes
RUN mvn -f service-solicitudes/pom.xml -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/service-solicitudes/target/service-solicitudes-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
FROM maven:3.8.4-openjdk-17-slim AS build

LABEL maintainer="veventumt"
LABEL org.opencontainers.image.title="veventumt/authforstock"
LABEL org.opencontainers.image.version="1.0.0"

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/AuthForStock-0.0.1-SNAPSHOT.jar ./AuthForStock-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "AuthForStock-0.0.1-SNAPSHOT.jar"]
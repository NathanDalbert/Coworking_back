
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app


COPY pom.xml .

RUN mvn dependency:go-offline

# Copiar o código fonte
COPY src /app/src

RUN mvn clean install -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/Coworking-*.jar coworking-backend.jar

# Definir opções de memória para o Java
ENV JAVA_OPTS="-Xms256m -Xmx512m"


ENTRYPOINT ["java", "-jar", "coworking-backend.jar"]


EXPOSE 8080

#FROM maven:3.9.9-eclipse-temurin-21 AS build
#WORKDIR /home/app
#COPY . /home/app
#RUN mvn -f /home/app/pom.xml clean install

FROM openjdk:21-jdk-slim
RUN apt-get update && apt-get install -y dnsutils   # to install nslookup
#VOLUME ["/tmp"]
#COPY --from=build /home/app/target/*.jar app.jar
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
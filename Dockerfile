FROM openjdk:11 as build
WORKDIR /app

RUN ./mvn clean
RUN ./mvn install

ENTRYPOINT ["java","-jar",""]
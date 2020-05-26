FROM openjdk:8-alpine

RUN mkdir /app

WORKDIR /app

COPY ./target/group14-0.0.1-SNAPSHOT.jar .

CMD java -jar group14-0.0.1-SNAPSHOT.jar

EXPOSE 8080
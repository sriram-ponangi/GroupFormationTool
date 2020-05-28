FROM openjdk:8-alpine

RUN mkdir /app

WORKDIR /app

COPY ./build/libs/group14-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar"] 

CMD ["-Dspring.profiles.active=test", "group14-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080 
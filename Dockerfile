FROM adoptopenjdk/openjdk11:alpine-jre

LABEL maintainer="danieldada123@gmail.com"

VOLUME /tmp

EXPOSE 5000/tcp

COPY build/libs/vending-machine-service-0.0.1-SNAPSHOT.jar vending-machine-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","vending-machine-service-0.0.1-SNAPSHOT.jar"]

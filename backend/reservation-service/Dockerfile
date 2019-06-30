FROM anapsix/alpine-java
MAINTAINER Vukasin Jovic <jovic.vukasin@gmail.com>
ADD target/reservation-service-0.0.1-SNAPSHOT.jar reservation-service.jar
ENTRYPOINT ["java", "-jar", "/reservation-service.jar"]
EXPOSE 8094

FROM anapsix/alpine-java
MAINTAINER Vukasin Jovic <jovic.vukasin@gmail.com>
ADD target/accommodation-service-0.0.1-SNAPSHOT.jar accommodation-service.jar
ENTRYPOINT ["java", "-jar", "/accommodation-service.jar"]
EXPOSE 8092

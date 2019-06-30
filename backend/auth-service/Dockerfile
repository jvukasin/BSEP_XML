FROM anapsix/alpine-java
MAINTAINER Vukasin Jovic <jovic.vukasin@gmail.com>
ADD target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "/auth-service.jar"]
EXPOSE 8093

FROM anapsix/alpine-java
MAINTAINER Vukasin Jovic <jovic.vukasin@gmail.com>
ADD target/MegaTravelAgent-0.0.1-SNAPSHOT.jar agent-service.jar
ENTRYPOINT ["java", "-jar", "/agent-service.jar"]
EXPOSE 8091

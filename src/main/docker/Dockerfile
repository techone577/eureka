FROM carsharing/alpine-oraclejdk8-bash
VOLUME /tmp
ADD eureka-0.0.1-SNAPSHOT.jar bsp-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/bsp-server.jar"]
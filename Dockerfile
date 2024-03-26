FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ./target/pruebatecnica-0.0.1-SNAPSHOT.jar prueba.jar
ENTRYPOINT ["java" , "-jar", "/prueba.jar"]

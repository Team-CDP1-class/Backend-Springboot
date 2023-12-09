FROM openjdk:17

COPY target/cdp1-0.0.1-SNAPSHOT.jar cdp1.jar

ENTRYPOINT ["java", "-jar", "/cdp1.jar"]
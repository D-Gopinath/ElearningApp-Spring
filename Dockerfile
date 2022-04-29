FROM openjdk:17-jdk
Volume /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
FROM openjdk:17
EXPOSE 8080
MAINTAINER Yasir-Shabbir
COPY target/SmartDubaiTest-1.0.jar smart-dubai-test-1.0.0.jar
ENTRYPOINT ["java","-jar","/smart-dubai-test-1.0.0.jar"]
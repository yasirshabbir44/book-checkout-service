FROM openjdk:11

COPY target/SmartDubaiTest-0.0.1-SNAPSHOT.jar SmartDubaiTestYasir.jar

ENTRYPOINT ["java","-jar","/SmartDubaiTestYasir.jar"]
FROM openjdk11:11.0.20.1-jre-hotspot
ADD target/achat-1.0.jar achat-1.0.jar
EXPOSE 8089
CMD ["java", "-jar", "/achat-1.0.jar"]
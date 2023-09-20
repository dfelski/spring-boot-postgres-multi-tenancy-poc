FROM eclipse-temurin:17-jre
RUN mkdir /opt/app
COPY target/application.jar /opt/app
CMD ["java", "-jar", "/opt/app/application.jar"]

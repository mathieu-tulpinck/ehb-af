FROM amazoncorretto:11-alpine-jdk
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY build/libs/webshop-0.0.1.jar /app/webshop-0.0.1.jar
COPY src/main/resources/application.properties /app/config/application.properties
ENTRYPOINT ["java","-jar","/app/webshop-0.0.1.jar"]
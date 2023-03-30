FROM eclipse-temurin:17-jdk-alpine
ENV AUTH_TOKEN "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"
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
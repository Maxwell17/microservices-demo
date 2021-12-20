FROM openjdk:17-alpine
RUN addgroup -S config && adduser -S config -G config
USER config:config
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /app/config.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app/config.jar"]
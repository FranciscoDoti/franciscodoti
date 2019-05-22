FROM value-capture/java
VOLUME /tmp
ADD target/pod-automation.jar /app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Dspring.profiles.active=ci"
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

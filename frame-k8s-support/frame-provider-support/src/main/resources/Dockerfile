FROM java:8
VOLUME /home
ADD frame-provider-support-0.0.2-SNAPSHOT.jar app1174580849316990976.jar
RUN sh -c 'touch /app1174580849316990976.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app1174580849316990976.jar" ]
FROM java:8
VOLUME /home
ADD frame-provider-support-0.0.1-SNAPSHOT.jar app1164444780894359552.jar
RUN sh -c 'touch /app1164444780894359552.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app1164444780894359552.jar" ]
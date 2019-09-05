FROM java:8
VOLUME /home
ADD frame-customer-support-0.0.1-SNAPSHOT.jar app1166165088105926656.jar
RUN sh -c 'touch /app1166165088105926656.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app1166165088105926656.jar" ]
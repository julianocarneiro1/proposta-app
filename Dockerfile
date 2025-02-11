FROM openjdk:17

COPY target/proposta-app-0.0.1-SNAPSHOT.jar proposta-app-0.0.1-SNAPSHOT.jar

COPY wait-for-it.sh wait-for-it.sh

RUN chmod +x wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "rabbit-mq:5672", "--", "java", "-Duser.country=BR", "-jar", "proposta-app-0.0.1-SNAPSHOT.jar"]
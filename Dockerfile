FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

RUN ./gradlew build
# /workspace/app/build/libs/*-SNAPSHOT.jar


FROM eclipse-temurin:21-jdk

COPY --from=build /workspace/app/build/libs/*-SNAPSHOT.jar app.jar
COPY script/entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]

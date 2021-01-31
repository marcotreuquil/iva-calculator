FROM openjdk:8-jdk-alpine AS BUILD

RUN mkdir -p /app/src
WORKDIR /app/src
COPY . /app/src

RUN chmod 777 gradlew

RUN ./gradlew build -x test

FROM openjdk:8-jdk-alpine as DEPLOY
ENV PORT 8080
EXPOSE 8080
COPY --from=BUILD /app/src/build/libs /opt/target
WORKDIR /opt/target

# Crear usuario
RUN adduser -D dockeruser

# Asignar permisos
RUN chown -R dockeruser /opt/target

# Se define el usuario a ejecutar
USER dockeruser
CMD ["/bin/sh", "-c", "find -type f -name 'iva-calculator-*.jar' | xargs java -jar"]

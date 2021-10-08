FROM openjdk:11
COPY target/classes  /myapp
COPY src/main/proto /myapp/target/classes
WORKDIR /myapp
ENTRYPOINT ["java", "net.HTTPServer"]
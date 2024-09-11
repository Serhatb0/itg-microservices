# Base image olarak OpenJDK 21 kullan
FROM openjdk:21-jdk-slim

# Uygulama jar dosyasını tanımla
ARG JAR_FILE=build/libs/discovery-server-0.0.1.jar

# Jar dosyasını container içine kopyala
COPY ${JAR_FILE} app.jar

# Uygulamanın çalışacağı portu belirt
EXPOSE 8080

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "/app.jar"]
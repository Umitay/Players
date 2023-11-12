FROM maven:3.8.5-openjdk-17

WORKDIR .
COPY . .
COPY player.csv /player.csv
RUN mvn clean install -U

CMD mvn spring-boot:run
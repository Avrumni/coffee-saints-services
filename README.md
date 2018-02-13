# Coffee Saints - Services

The best way to coffee! Find out who's turn it is to buy coffee :)

## Requirements

* Java, Maven
* Postgresql
* User setup

```
psql
create database "coffee-saints"
CREATE ROLE "bean-god" WITH LOGIN PASSWORD 'password';
```

## Starting

```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/coffee-saints SPRING_DATASOURCE_USERNAME=bean-god SPRING_DATASOURCE_PASSWORD=password mvn install spring-boot:run
```
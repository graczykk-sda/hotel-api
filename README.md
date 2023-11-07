# hotel-api
Api design for hotel vendors

## Requirements to build and run
Java 17

## Build

To build project execute:

```
./gradlew build
```

## Run integration tests

```
./gradlew integrationTest
```
Test reports can be found in `build/reports/tests/test` and `build/reports/tests/integrationTest`

## Run application

Executable jar can be found in `build/libs/api-0.0.1-SNAPSHOT.jar`

Execute this jar with:

```
java -jar api-0.0.1-SNAPSHOT.jar
```

As actuator is enabled in this project, you can check if application is working with health endpoint:

```
http://localhost:8080/actuator/health
```

## Test application

When application is running you can execute requests to it using postman or any other tools like curl. 
For example:

```
curl -X POST \
  http://localhost:8080/v1/accommodation \
  -H 'Content-Type: application/json' \
  -d '{
	"premium" : 3,
	"economy" : 3
}'
```

## Postman collection  

There is also example postman collection that you can use to check api. Those collections are located in `postman` folder.
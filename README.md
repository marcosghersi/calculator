## About the project

Java version: 17
maven version: 3.x

Execution using 'mvn' installed in local system or using maven wrapper: ./mvnw

## Compile project

Firstly execute 'mvn validate' in the root directory of the project to install tracer-1.0.0.jar in local repository

## Unit tests

### Run all tests

execute 'mvn test' in the root directory of the project

## Run application

OPTION 1: execute 'mvn spring-boot:run' in the root directory of the project

OPTION 2: build project executing 'mvn clean install' in the root directory of the project and then execute 'java -jar
target/calculator-0.0.1-SNAPSHOT.jar'

### Test REST API running application

On browser or Postman, using curl command in local system, use this example requests:

GET http://localhost:8080/calculator/ADDITION/5/0.5?precision=0&roundingMode=HALF_DOWN

GET http://localhost:8080/calculator/SUBTRACTION/5/0.5?precision=0&roundingMode=HALF_DOWN

roundingMode and precision are optional parameters.

Two operations are available: ADDITION and SUBTRACTION

roundingMode possible values: UP, DOWN, CEILING, FLOOR, HALF_UP, HALF_DOWN, HALF_EVEN, UNNECESSARY
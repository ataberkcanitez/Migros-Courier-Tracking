# Migros-Courier-Tracking

A Restful web application that trracks the streaming geolocations of couriers and provides the total distance travelled by each courier.


## TechStack
 - Java17
 - Spring Boot
 - MySQL
 - Docker

## How To Run With Docker
    - Clone the repository
```bash
$ git clone git@github.com:ataberkcanitez/Migros-Courier-Tracking.git
```
- Navigate to the project directory:
 ```bash
$ cd Migros-Courier-Tracking
 ```
- Run `docker-compose up` in the root directory
```bash
$ docker-compose up
 ```

## Testing:
- To execute unit tests:
```bash
$ mvn clean test
```

- To test the api's;
    - First you have to run `docker-compose up`. Then you can use the given `postman` collections. 
     
//TODO: postman collection not provided yet.





# Springboot Application with Oracle ActiveMQ

Refer to following classes
* [JmsConfig](src/main/java/com/example/springboot/JmsConfig.java)
* [JmsMessageSender](src/main/java/com/example/springboot/jms/JmsMessageSender.java)
* [JmsMessageListener](src/main/java/com/example/springboot/jms/JmsMessageListener.java)

### How to build
```shell
./gradlew clean build -x test && java -jar build/libs/spring-boot-0.0.1-SNAPSHOT.war 
```
### How to test

```shell
	curl --location --request POST 'http://localhost:8080/oraclejms/send?msg=hello' \
--data-raw ''
```
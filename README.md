References

[Sample](http://zetcode.com/articles/springwebfirst/)

[https://octopus.com/blog/defining-tomcat-context-paths](https://octopus.com/blog/defining-tomcat-context-paths)

To change the context root in tomcat copy the file [test#testservice.xml](./test#testservice.xml) to {tomcat}/conf/Catalina/localhost


## How to test 

The code runs on test/testservice context path

### Test req
POST
```
curl --location --request POST 'http://localhost:8080/test/testservice/req' \
--header 'Content-Type: application/json' \
--data-raw '{
    "requestType" : "Request2",
    "reqDef" : {
        "param1" : "parameter1Value",
        "param2" : "parameter2 Value"
    }
}'
```
-- build from number2 dir
mvn clean install

-- run using 
java -cp target/number2-0.0.1-SNAPSHOT-jar-with-dependencies.jar server.ServerConfigurationMain

-- post to endpoint localhost:8080/submit
curl -H "Content-Type: application/json" -X POST -d '{
    "id": 123,
    "name": "Sample Name",
    "active": true,
    "count": 1000,
    "address_ids": [
        1,
        30,
        100,
        50
    ],
    "accounts": [
        {
            "id": 1,
            "name": "Test Account 1"
        },
        {
            "id": 2,
            "name": "Test Account 2"
        }
    ]
}' http://localhost:8080/submit

-- output will print to logs/number2.log via log4j
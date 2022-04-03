# phonebook

##Prerequisites
To run this project you need Maven 3, Java 17, Docker and Docker compose setup.

##How to run the project

````
mvn clean package
docker compose build
docker compose up -d
````

After the docker is up and running you can reach the API on port 8080.

## Operations

The application is listening on the context path `/phonebook` 

### GET /contacts
````
curl --location --request GET 'localhost:8080/phonebook/contacts/'
````
Calling this endpoint will return all contacts saved in the database, including related phone-numbers.

### POST /contacts
````
curl --location --request POST 'localhost:8080/phonebook/contacts/' \
--header 'Content-Type: application/json' \
--data-raw '{ "name": "Test 3" }'
````
Add a new contact to the database, will auto generate a unique id.

### GET /contacts/{id}
Fetch a contact by id, will return 204 no content if no match is found.
# phonebook
A small demo project showing a simple phone book, where you can manage contacts and phone numbers.
Running docker compose it will use the `target/*.jar` to create a standalone docker with a MariaDB as datasource.
The MariaDB is pre-populated with 2 contacts and 3 phone number to be able to demonstrate the API functionality.
##Prerequisites
To run this project you need Maven 3, Java 17, Docker and Docker compose setup.

##How to run the project
Either use the start and stop shell scripts, or manually build and call docker compose.

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

### POST /contacts/{id}/phonenumber
````
curl --location --request POST 'localhost:8080/phonebook/contacts/1/phonenumber' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number":"0716492886",
    "typeOfNumber": "work"
}'
````
Add a phone number to contact with {id} 

### GET /phonenumber/{number}
Do a search for phone number beginning with or equals the {number} input.

### DELETE /phonenumber/{nubmer}
Delete number matching {number} input, or return BAD REQUEST if number does not exist.
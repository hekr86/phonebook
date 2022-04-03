DROP DATABASE IF EXISTS demo;
CREATE DATABASE demo;
USE demo;

CREATE TABLE contact
(
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64)
);

INSERT INTO contact (name) VALUES ("Henrik Ha"),("Emil Ha");

CREATE TABLE phone_number
(
    number VARCHAR(32) PRIMARY KEY,
    type VARCHAR(64),
    contact_id INT REFERENCES contact(contact_id)
);

INSERT INTO phone_number VALUES ("0701234500","Work",1),("0702234500","Home",1),("0702234501","Home",2);
CREATE OR REPLACE USER 'hekr'@'%' IDENTIFIED BY 'password1234';
GRANT USAGE ON *.* TO 'hekr'@'%';
GRANT ALL PRIVILEGES ON *.* TO 'hekr'@'%' WITH GRANT OPTION;

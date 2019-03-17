CREATE SCHEMA tastingapp;

USE tastingapp;

CREATE TABLE user (
	id BIGINT(20) NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE beer(
	id BIGINT(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(1000),
    alchoholAmount FLOAT(100, 2) NOT NULL,
    PRIMARY KEY (id)
);
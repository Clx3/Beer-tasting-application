CREATE SCHEMA tastingapp;

USE tastingapp;

CREATE TABLE user (
	id BIGINT(20) NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
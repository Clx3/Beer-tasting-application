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
    beerName VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(1000),
    alcoholPercent FLOAT(100, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tastingSession(
	id BIGINT(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    startingDate DATETIME NOT NULL,
    additionalInfo VARCHAR(2000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE beerAndTastingSession(
	id BIGINT(20) NOT NULL,
    beerId BIGINT(20) NOT NULL,
    tastingSessionId BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (beerId) REFERENCES beer(id),
    FOREIGN KEY (tastingSessionId) REFERENCES tastingSession(id)
);

CREATE TABLE user_and_tastingsession(
	id BIGINT(20) NOT NULL,
    userId BIGINT(20) NOT NULL,
    tastingSessionId BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (tastingSessionId) REFERENCES tastingSession(id)
);

CREATE TABLE rating(
	id BIGINT(20) NOT NULL,
    userId BIGINT(20) NOT NULL,
    beerId BIGINT(20) NOT NULL,
    comment VARCHAR(500),
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user(id),
    FOREIGN KEY (beerId) REFERENCES beer(id)
);

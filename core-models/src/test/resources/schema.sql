DROP TABLE PERSON
IF EXISTS;
CREATE TABLE PERSON (
  id        INT IDENTITY,
  firstName VARCHAR(40),
  lastName  VARCHAR(40)
);
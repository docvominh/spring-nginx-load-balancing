--liquibase formatted sql
--changeset liquibase:1

CREATE TABLE product
(
    id          INT NOT NULL PRIMARY KEY,
    category    VARCHAR(100),
    name        VARCHAR(100),
    manufacture VARCHAR(50),
    price       REAL
);

CREATE SEQUENCE common
    INCREMENT 1
    START 1000
    MINVALUE 1
    MAXVALUE 100000000;
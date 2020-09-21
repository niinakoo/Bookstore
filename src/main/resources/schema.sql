DROP TABLE book;
DROP TABLE category;

CREATE TABLE category
(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY)
, name VARCHAR(50) NOT NULL);

CREATE TABLE book (
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(50) NOT NULL,
author VARCHAR(50) NOT NULL,
year INT,
isbn VARCHAR(25),
price BIGINT,
categoryid BIGINT);

INSERT INTO category (name) VALUES ('Dekkari'), ('Novelli'), ('Fantasia');

INSERT INTO book (title, author, year, price, categoryid)
VALUES ('Suon villi laulu', 'Delia Owens', 2020, 29, 1),
('Korkeintaan vahan vasynyt', 'Eeva Kolu', 2020, 30, 2);

SELECT * FROM book;
SELECT * FROM category;
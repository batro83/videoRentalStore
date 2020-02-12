
-- Table User 
DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer (
  id_Customer INT PRIMARY KEY NOT NULL,
  name VARCHAR(250) NOT NULL,
  points INT
);

INSERT INTO Customer (id_Customer, name, points) VALUES
  (1, 'user1', 10),
  (2, 'user2', 20);


-- Table TypeFilm 
DROP TABLE IF EXISTS Type_Film;

CREATE TABLE Type_Film (
  id_Type_Film INT PRIMARY KEY NOT NULL,
  description VARCHAR(250) NOT NULL,
  points INT NOT NULL,
  price INT NOT NULL
);

INSERT INTO Type_Film (id_Type_Film, description, points, price) VALUES
  (1, 'New releases', 2, 3),
  (2, 'Regular films', 1, 1),
  (3, 'Old film', 1, 1);


-- Table Film 
DROP TABLE IF EXISTS film;

DROP SEQUENCE IF EXISTS pk_film_seq;
CREATE SEQUENCE pk_film_seq start 1;
 
CREATE TABLE film (
  id_Film INT not NULL DEFAULT nextval('pk_film_seq') PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  category VARCHAR(50) NOT NULL,
  date_Created DATE NOT NULL,
  id_Type_Film INT,
  FOREIGN KEY (id_Type_Film) REFERENCES Type_Film(id_Type_Film)
);
 
INSERT INTO film (name, category, date_Created, id_Type_Film) VALUES
  ('Aliko', 'Terror', now(), 1),
  ('Bill', 'Comedy', now(), 2),
  ('Folrunsho', 'Terror', now(), 3),
  ('FilmC', 'Comedy', now(), 1),
  ('FilmD', 'Comedy', now(), 2),
  ('FilmE', 'Thriller', now(), 3),
  ('FilmF', 'Comedy', now(), 1),
  ('FilmG', 'Terror', now(), 1),
  ('FilmH', 'Thriller', now(), 3);  
  
-- Table Rent 
DROP TABLE IF EXISTS rent;

DROP SEQUENCE IF EXISTS pk_rent_seq;
CREATE SEQUENCE pk_rent_seq start 1;
 
CREATE TABLE rent (
  id_Rent INT not NULL DEFAULT nextval('pk_rent_seq') PRIMARY KEY,
  id_Customer INT NOT NULL,
  id_Film INT NOT NULL,
  days INT NOT NULL,
  date_rent DATE NOT NULL,
  date_return DATE,
  points INT NOT NULL,
  FOREIGN KEY (id_Customer) REFERENCES Customer(id_Customer),
  FOREIGN KEY (id_Film) REFERENCES Film(id_Film)
);
 

INSERT INTO rent (id_Customer, id_Film, date_rent, points, days) VALUES
  (1, 8, now()- interval '2 days', 2, 4),
  (1, 2, now()- interval '2 days', 1, 4);

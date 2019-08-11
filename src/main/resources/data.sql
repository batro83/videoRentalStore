
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
 
CREATE TABLE film (
  id_Film INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  category VARCHAR(250) NOT NULL,
  date_Created DATE NOT NULL,
  id_Type_Film INT
);
 
INSERT INTO film (name, category, date_Created, id_Type_Film) VALUES
  ('Aliko', 'Terror', sysdate, 1),
  ('Bill', 'Terror', sysdate, 2),
  ('Folrunsho', 'Terror', sysdate, 3);
  
  
  
-- Table Rent 
DROP TABLE IF EXISTS rent;
 
CREATE TABLE rent (
  id_Rent INT AUTO_INCREMENT  PRIMARY KEY,
  id_Customer INT NOT NULL,
  id_Film INT NOT NULL,
  date_rent DATE NOT NULL,
  date_return DATE,
  points INT NOT NULL
);
 
INSERT INTO rent (id_Customer, id_Film, date_rent, points) VALUES
  (1, 1, sysdate, 1),
  (1, 2, sysdate, 2);
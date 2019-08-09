
-- Table User 
DROP TABLE IF EXISTS User;

CREATE TABLE User (
  id_User INT PRIMARY KEY NOT NULL,
  name VARCHAR(250) NOT NULL,
  points INT
);

INSERT INTO User (id_User, name, points) VALUES
  (1, 'user1', 10),
  (2, 'user2', 20);


-- Table TypeFilm 
DROP TABLE IF EXISTS Type_Film;

CREATE TABLE Type_Film (
  id_Type_Film INT PRIMARY KEY NOT NULL,
  description VARCHAR(250) NOT NULL
);


INSERT INTO Type_Film (id_Type_Film, description) VALUES
  (1, 'New releases'),
  (2, 'Regular films'),
  (3, 'Old film');


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
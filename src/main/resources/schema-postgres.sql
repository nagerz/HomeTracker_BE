DROP TABLE IF EXISTS rooms;
CREATE TABLE rooms(id serial PRIMARY KEY, name VARCHAR(100), description VARCHAR(100), type VARCHAR(100));

INSERT INTO rooms(name, description, type) VALUES('Living Room 1', 'Northeast living room', 'Living Room');

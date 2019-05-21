DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS rooms;

CREATE TABLE projects(id serial PRIMARY KEY, name VARCHAR(100), description VARCHAR(100), address VARCHAR(100));
CREATE TABLE rooms(id serial PRIMARY KEY, name VARCHAR(100), description VARCHAR(100), type VARCHAR(100));

INSERT INTO projects(name, description, address) VALUES('House 1', 'Big, white house', '123 Fake St.');
INSERT INTO projects(name, description, address) VALUES('House 2', 'Small with picket fence', '456 Random Lane');

INSERT INTO rooms(name, description, type) VALUES('Living Room 1', 'Northeast living room', 'Living Room');
INSERT INTO rooms(name, description, type) VALUES('Room 2', 'Big Kitchen', 'Kitchen');
INSERT INTO rooms(name, description, type) VALUES('Room 3', 'Small bedroom', 'Bedroom');

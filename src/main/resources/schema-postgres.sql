DROP TABLE IF EXISTS projects cascade;
DROP TABLE IF EXISTS rooms cascade;
DROP TABLE IF EXISTS materials cascade;
DROP TABLE IF EXISTS room_materials;

CREATE TABLE projects(id serial PRIMARY KEY, name VARCHAR(100), description VARCHAR(100), address VARCHAR(100), city VARCHAR(100), state VARCHAR(100), zip_code VARCHAR(100));
CREATE TABLE rooms(id serial PRIMARY KEY, project_id int, name VARCHAR(100), description VARCHAR(100), type VARCHAR(100));
CREATE TABLE materials(id serial PRIMARY KEY, name VARCHAR(100), model_number VARCHAR(100),
                        brand VARCHAR(100), vendor VARCHAR(100), manual_url VARCHAR(100), notes VARCHAR(300),
                        quantity real, unit_price real);
CREATE TABLE room_materials(id serial PRIMARY KEY, element_type VARCHAR(100), room_id int, material_id int);

INSERT INTO projects(name, description, address, city, state, zip_code) VALUES('House 1', 'Big, white house', '123 Fake St.', 'Denver', 'CO', '80205');
INSERT INTO projects(name, description, address, city, state, zip_code) VALUES('House 2', 'Small with picket fence', '456 Random Lane', 'Denver', 'CO', '80205');

INSERT INTO rooms(name, project_id, description, type) VALUES('Living Room 1', 1,'Northeast living room', 'Living Room');
INSERT INTO rooms(name, project_id, description, type) VALUES('Room 2', 1, 'Big Kitchen', 'Kitchen');
INSERT INTO rooms(name, project_id, description, type) VALUES('Room 3', 2, 'Small bedroom', 'Bedroom');

INSERT INTO materials(name, vendor, brand, model_number) VALUES('Material 1', 'HD', 'Kenmoore', 'abc1');
INSERT INTO materials(name, vendor, brand, model_number) VALUES('Material 2', 'HD', 'Kenmoore', 'abc2');
INSERT INTO materials(name, vendor, brand, model_number) VALUES('Material 3', 'HD', 'Kenmoore', 'abc3');
INSERT INTO materials(name, vendor, brand, model_number) VALUES('Material 4', 'HD', 'Kenmoore', 'abc4');
INSERT INTO materials(name, vendor, brand, model_number) VALUES('Material 5', 'HD', 'Kenmoore', 'abc5');

INSERT INTO room_materials(element_type, room_id, material_id) VALUES('Shower', 1, 1);
INSERT INTO room_materials(element_type, room_id, material_id) VALUES('Shower', 1, 2);
INSERT INTO room_materials(element_type, room_id, material_id) VALUES('Shower', 2, 1);

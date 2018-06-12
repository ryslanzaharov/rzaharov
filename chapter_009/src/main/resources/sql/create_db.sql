CREATE TABLE engine(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50),
  type_engine VARCHAR(50),
  condition VARCHAR(50)
);

CREATE TABLE car
(
  id SERIAL PRIMARY KEY,
  mark character varying(30),
  model VARCHAR(50),
  body_type VARCHAR(20),
  price INTEGER,
  engine_id integer NOT NULL REFERENCES engine(id)
);

CREATE TABLE condition(
  id SERIAL PRIMARY KEY,
  condition VARCHAR(50),
  year INTEGER,
  mileage INTEGER,
  id_car integer NOT NULL REFERENCES car(id)
);


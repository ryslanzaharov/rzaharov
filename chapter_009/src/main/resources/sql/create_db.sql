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
  engine_id integer NOT NULL REFERENCES engine(id),
  condition_id integer NOT NULL REFERENCES condition(id),
  user_id integer NOT NULL REFERENCES users(id)
);

CREATE TABLE condition(
  id SERIAL PRIMARY KEY,
  condition VARCHAR(50),
  year INTEGER,
  mileage INTEGER
);

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  login VARCHAR(20),
  password VARCHAR(20),
  create_date timestamp without time zone
);


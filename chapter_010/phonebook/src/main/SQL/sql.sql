CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username varchar(50),
  password varchar(50),
  firstname varchar(50),
  lastname varchar(50),
  patronymic varchar(50),
  enabled boolean default true,
  roles_id integer references roles(id)
);

CREATE TABLE phone_book (
  id SERIAL PRIMARY KEY,
  users_id integer references users(id),
  firstname varchar(50),
  lastname varchar(50),
  patronymic varchar(50),
  telephone_number varchar(15),
  home_phone_number varchar(10),
  address text,
  e_mail varchar
);

CREATE TABLE roles(
id SERIAL PRIMARY KEY,
roles_name varchar
);
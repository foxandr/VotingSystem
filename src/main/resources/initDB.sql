DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS votes;

CREATE SEQUENCE start_index START 1;

CREATE TABLE users (
  id  INTEGER PRIMARY KEY DEFAULT nextval('start_index'),
  name VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  registred TIMESTAMP DEFAULT now()
);

CREATE UNIQUE INDEX users_idx ON users(email);

CREATE TABLE user_roles (
  user_id INTEGER NOT NULL,
  role VARCHAR NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
  id INTEGER PRIMARY KEY DEFAULT nextval('start_index'),
  name VARCHAR NOT NULL,
  address VARCHAR NOT NULL,
  registred TIMESTAMP DEFAULT now()
);

CREATE UNIQUE INDEX restaurants_idx ON restaurants(address, name);

CREATE TABLE dishes (
  rest_id INTEGER NOT NULL,
  name VARCHAR NOT NULL,
  price INTEGER DEFAULT 10,
  updated TIMESTAMP DEFAULT now(),
  FOREIGN KEY (rest_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX dishes_idx ON dishes (rest_id, name);

CREATE TABLE votes (
  user_id INTEGER PRIMARY KEY NOT NULL,
  rest_id INTEGER DEFAULT 0,
  voted TIMESTAMP DEFAULT now(),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX votes_idx ON votes(user_id);
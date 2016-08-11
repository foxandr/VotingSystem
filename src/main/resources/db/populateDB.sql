DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM votes;
ALTER SEQUENCE start_index RESTART 1;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@votes.by', 'megaadmin'),
  ('User1', 'user1@votes.by', 'simpleuser'),
  ('User2', '2thUser@votes.by', 'user');

INSERT INTO user_roles (user_id, role) VALUES
  (1, 'ROLE_USER'),
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER'),
  (3, 'ROLE_USER');

INSERT INTO restaurants (name, address) VALUES
  ('Кронон', 'Пышки 1'),
  ('Семашко', 'Антонова 10'),
  ('Карчма', 'Советская 31');

INSERT INTO dishes (id, rest_id, name, price) VALUES
  (7, 4, 'Шашлык из говяжей вырезки', 12),
  (8, 4, 'Салат "Белорусский"', 9.5),
  (9, 4, 'Грибное лукошко', 9),
  (10, 4, 'Клубничный макарун', 6.5),
  (11, 5, 'Салат "Цезарь"', 6.9),
  (12, 5, 'Стейк из лосося', 15.4),
  (13, 5, 'Спагетти "Наполетано"', 10.5),
  (14, 5, 'Салянка мясная', 8.8),
  (15, 6, 'Салат "Греческий"', 12.1),
  (16, 6, 'Тигровые креветки', 20.4),
  (17, 6, 'КАртофель с грибами', 6.6);

INSERT INTO votes (user_id, rest_id) VALUES
  (1, 4),
  (2, 4),
  (3, 5);
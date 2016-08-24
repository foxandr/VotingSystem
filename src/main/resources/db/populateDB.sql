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

INSERT INTO dishes (rest_id, name, price) VALUES
  (4, 'Шашлык из говяжей вырезки', round(12, 1)),
  (4, 'Салат "Белорусский"', round(9.5, 1)),
  (4, 'Грибное лукошко', round(9, 1)),
  (4, 'Клубничный макарун', round(6.5, 1)),
  (5, 'Салат "Цезарь"', round(6.9, 1)),
  (5, 'Стейк из лосося', round(15.4, 1)),
  (5, 'Спагетти "Наполетано"', round(10.5, 1)),
  (5, 'Салянка мясная', round(8.8, 1)),
  (6, 'Салат "Греческий"', round(12.1, 1)),
  (6, 'Тигровые креветки', round(20.4, 1)),
  (6, 'Картофель с грибами', round(6.6, 1));

INSERT INTO votes (user_id, rest_id) VALUES
  (1, 4),
  (2, 4),
  (3, 5);
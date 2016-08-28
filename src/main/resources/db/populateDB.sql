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

INSERT INTO dishes (rest_id, name, price, updated) VALUES
  (4, 'Шашлык из говяжей вырезки', 12, current_date),
  (4, 'Салат "Белорусский"', 9.5, current_date),
  (4, 'Грибное лукошко', 9, current_date),
  (4, 'Клубничный макарун', 6.5, current_date),
  (5, 'Салат "Цезарь"', 6.9, current_date),
  (5, 'Стейк из лосося', 15.4, current_date),
  (5, 'Спагетти "Наполетано"', 10.5, current_date),
  (5, 'Салянка мясная', 8.8, current_date),
  (6, 'Салат "Греческий"', 12.1, current_date),
  (6, 'Тигровые креветки', 20.4, current_date),
  (6, 'Картофель с грибами', 6.6, current_date);

INSERT INTO votes (user_id, rest_id, voted) VALUES
  (1, 4, timestamp '2016-08-27 09:00'),
  (2, 4, timestamp '2016-08-27 09:10'),
  (3, 5, timestamp '2016-08-27 09:30');
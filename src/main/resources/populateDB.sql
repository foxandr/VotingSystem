DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM votes;
ALTER SEQUENCE start_index RESTART 1;

INSERT INTO users (name, email, password)
    VALUES ('Admin', 'admin@votes.by', 'megaadmin'),
    VALUES ('User1', 'user1@votes.by', 'simpleuser'),
    VALUES ('User2', '2thUser@votes.by', 'user');

INSERT INTO user_roles (user_id, role)
    VALUES (1, 'ROLE_USER'),
    VALUES (1, 'ROLE_ADMIN'),
    VALUES (2, 'ROLE_USER'),
    VALUES (3, 'ROLE_USER');

INSERT INTO restaurants (name, address)
    VALUES ('Кронон', 'Пышки 1'),
    VALUES ('Семашко', 'Антонова 10'),
    VALUES ('Карчма', 'Советская 31');

INSERT INTO dishes (rest_id, name, price)
    VALUES (4, 'Шашлык из говяжей вырезки', 12),
    VALUES (4, 'Салат "Белорусский"', 9.5),
    VALUES (4, 'Грибное лукошко', 9),
    VALUES (4, 'Клубничный макарун', 6.5),
    VALUES (5, 'Салат "Цезарь"', 6.9),
    VALUES (5, 'Стейк из лосося', 15.4),
    VALUES (5, 'Спагетти "Наполетано"', 10.5),
    VALUES (5, 'Салянка мясная', 8.8),
    VALUES (6, 'Салат "Греческий"', 12.1),
    VALUES (6, 'Тигровые креветки', 20.4),
    VALUES (6, 'КАртофель с грибами', 6.6);

INSERT INTO votes (user_id, rest_id)
    VALUES (1, 4),
    VALUES (2, 4),
    VALUES (3, 5);
-- Добавляем пользователей
INSERT INTO roles (id, name) VALUES (0, 'ROLE_ADMIN'), (1, 'ROLE_USER');
INSERT INTO users (id, password, username) VALUES (0, '$2a$10$/U2kryZX8zTqTTzzGZgSxOeOrwoBdBtbdQnJhsFftt1fnuQDJFgom', 'admin');
INSERT INTO users (id, password, username) VALUES (1, '$2a$10$kVLzsV/JMxv6ZCGl7.CeLeOpPQ2/cCF/uiiYspDYNvuDMHpNDRgZy', 'user');
INSERT INTO users_roles (user_id, role_id) VALUES (0, 0);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);

-- Добавляем товары
INSERT INTO product (id, title, cost) VALUES
(0, 'Телевизор', 20000),
(1, 'Утюг', 3000),
(2, 'Кофеварка', 3500),
(3, 'Стиральная машина', 3000),
(4, 'Раковина', 7900),
(5, 'Ванна', 12300),
(6, 'Бомбочка для ванны', 300),
(7, 'Фен', 2000),
(8, 'Дверь', 40000),
(9, 'Окно', 35500),
(10, 'Фонарь', 54000),
(11, 'Поребрик', 99900);
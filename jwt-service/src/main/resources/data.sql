INSERT INTO roles (id, name) VALUES (0, 'ROLE_ADMIN'), (1, 'ROLE_USER');
INSERT INTO users (id, password, username) VALUES (0, '$2a$10$/U2kryZX8zTqTTzzGZgSxOeOrwoBdBtbdQnJhsFftt1fnuQDJFgom', 'admin');
INSERT INTO users (id, password, username) VALUES (1, '$2a$10$kVLzsV/JMxv6ZCGl7.CeLeOpPQ2/cCF/uiiYspDYNvuDMHpNDRgZy', 'user');
INSERT INTO users_roles (user_id, role_id) VALUES (0, 0);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
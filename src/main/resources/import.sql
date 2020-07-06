DROP TABLE users IF EXISTS;
CREATE TABLE IF NOT EXISTS users (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO users (name) VALUES ('Bob');

DROP TABLE items IF EXISTS;
CREATE TABLE IF NOT EXISTS items (id bigserial, title VARCHAR(255), price int, PRIMARY KEY (id));
INSERT INTO items (title, price) VALUES ('box','100');

DROP TABLE users_items IF EXISTS;
CREATE TABLE IF NOT EXISTS users_items (user_id bigserial, item_id bigserial);
INSERT INTO users_items (user_id, item_id) VALUES (1,1);
INSERT INTO users_items (user_id, item_id) VALUES (1,3);
INSERT INTO users_items (user_id, item_id) VALUES (1,5);
INSERT INTO users_items (user_id, item_id) VALUES (2,5);
INSERT INTO users_items (user_id, item_id) VALUES (3,3);
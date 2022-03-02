CREATE TABLE product (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name TEXT,
    price DOUBLE PRECISION,
    quantity INTEGER
);

INSERT INTO product (name, price, quantity) values ('Pencil', 2.5, 20);
INSERT INTO product (name, price, quantity) values ('Balloon', 0.5, 200);
INSERT INTO product (name, price, quantity) values ('Computer', 1222, 5);
INSERT INTO product (name, price, quantity) values ('Watch', 12.7, 15);
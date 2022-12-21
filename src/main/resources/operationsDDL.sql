create table IF NOT EXISTS products
(
    id    serial
        constraint products_pk
            primary key,
    name  varchar(50),
    price float
);

create table IF NOT EXISTS cards
(
    id      serial
        constraint cards_pk
            primary key,
    name    varchar(50),
    percent int
);

INSERT INTO cards(id, name, percent) VALUES (1, 'card-1234', 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO cards(id, name, percent) VALUES (2, 'card-1235', 3) ON CONFLICT (id) DO NOTHING;;
INSERT INTO cards(id, name, percent) VALUES (3, 'card-1236', 5) ON CONFLICT (id) DO NOTHING;;


INSERT INTO products (id, name, price) VALUES (1, 'Milk', 10) ON CONFLICT (id) DO NOTHING;;
INSERT INTO products (id, name, price) VALUES (2, 'Meat', 5) ON CONFLICT (id) DO NOTHING;;
INSERT INTO products (id, name, price) VALUES (3, 'Cheese', 3) ON CONFLICT (id) DO NOTHING;;
INSERT INTO products (id, name, price) VALUES (4, 'Cookie', 4) ON CONFLICT (id) DO NOTHING;;
INSERT INTO products (id, name, price) VALUES (5, 'Flour', 8) ON CONFLICT (id) DO NOTHING;;
INSERT INTO products (id, name, price) VALUES (6, 'Juice', 7) ON CONFLICT (id) DO NOTHING;;
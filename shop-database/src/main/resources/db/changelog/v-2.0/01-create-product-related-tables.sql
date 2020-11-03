CREATE TABLE brands
(
    id   bigserial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE
);
GO

CREATE TABLE categories
(
    id   bigserial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE
);
GO

CREATE TABLE products
(
    id          bigserial PRIMARY KEY,
    name        varchar(255) NOT NULL,
    price       numeric(19, 2) DEFAULT NULL,
    brand_id    bigint NOT NULL,
    category_id bigint NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);
GO


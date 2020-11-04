CREATE TABLE brands
(
    id   bigserial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE
);
GO

CREATE TABLE categories
(
    id   serial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE
);
GO

CREATE TABLE statuses
(
    id   serial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE
);
GO

CREATE TABLE product_types
(
    id   serial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE
);
GO

CREATE TABLE products
(
    id          bigserial PRIMARY KEY,
    name        varchar(255)                                            NOT NULL,
    quick_desc  varchar(255)                                            NOT NULL,
    description text                                                    NOT NULL,
    size        smallint,
    discount    smallint CHECK (discount between 0 AND 100) DEFAULT (0),
    price       numeric(19, 2)                              DEFAULT NULL,
    category_id int                                                     NOT NULL,
    brand_id    bigint                                                  NOT NULL,
    type_id     int                                                     NOT NULL,
    status_id   int                                         DEFAULT (1) NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands (id),
    FOREIGN KEY (category_id) REFERENCES categories (id),
    FOREIGN KEY (status_id) REFERENCES statuses (id),
    FOREIGN KEY (type_id) REFERENCES product_types (id)
);
GO


CREATE TABLE pictures_data
(
    id   bigserial PRIMARY KEY,
    data bytea NOT NULL
);
GO

CREATE TABLE pictures
(
    id              bigserial PRIMARY KEY,
    content_type    varchar(255) NOT NULL,
    name            varchar(255) NOT NULL,
    picture_data_id bigint       NOT NULL UNIQUE,
    FOREIGN KEY (picture_data_id) REFERENCES pictures_data (id)
);
GO

CREATE TABLE products_pictures
(
    product_id bigint NOT NULL,
    picture_id bigint NOT NULL,
    PRIMARY KEY (picture_id, product_id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (picture_id) REFERENCES pictures (id)
);
GO

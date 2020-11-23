CREATE TABLE orders
(
    id      bigserial PRIMARY KEY,
    user_id bigint         NOT NULL,
    price   numeric(19, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
GO

CREATE TABLE order_items
(
    id         bigserial PRIMARY KEY,
    product_id bigint         NOT NULL,
    order_id   bigint         NOT NULL,
    price      numeric(19, 2) NOT NULL,
    quantity   int            NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (order_id) REFERENCES orders (id)
);
GO


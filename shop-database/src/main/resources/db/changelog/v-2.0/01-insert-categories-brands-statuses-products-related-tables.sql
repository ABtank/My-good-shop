INSERT INTO statuses (name)
VALUES ('Хит'),
       ('Новинка'),
       ('Распродажа'),
       ('Акция'),
       ('Уценка'),
       ('Рассрочка');
GO

INSERT INTO brands (name)
VALUES ('LOVE REPUBLIC'),
       ('Levis'),
       ('Persol'),
       ('Nike'),
       ('Adidas'),
       ('Edwin'),
       ('New Balance'),
       ('Paul Smith'),
       ('Ray-Ban');
GO

INSERT INTO categories (name)
VALUES ('Мужское'),
       ('Женское'),
       ('Детское'),
       ('Аксессуары'),
       ('Мебель');
GO

INSERT INTO product_types (name)
VALUES ('Диван-кровать'),
       ('Сумки'),
       ('Шапки и шляпы'),
       ('Куртки и пальто'),
       ('Джинсы'),
       ('Рубашки'),
       ('Солнцезащитные очки'),
       ('Купальники');
GO

INSERT INTO products (name, quick_desc, description, size, category_id, brand_id, type_id)
VALUES ('Jan Steen','Пуховик ','Пуховик Пуховик Пуховик ',54,1,1,4),
       ('Amimoda','Куртка ','Куртка Куртка Куртка Куртка ',46,1,2,4),
       ('adidas Originals Pad','Куртка ','Куртка adidas Originals Pad Hooded Puff',54,1,5,4),
       ('Куртка ТВОЕ','Куртка ','Куртка ТВОЕКуртка ТВОЕКуртка ТВОЕ ',42,2,1,4),
       ('Jan Steen','Пуховик ','Пуховик Пуховик Пуховик ',54,2,1,4),
       ('Пальто LOVE','Пальто ','Пальто утепленное LOVE REPUBLIC ',54,2,1,4),
       ('Юбка Jan','Юбка ','Юбка Юбка Юбка Юбка ',164,3,2,5),
       ('Пинетки Steen','Пинетки  ','Пинетки Пинетки Пинетки  ',68,3,3,5),
       ('Jan Steen','Пуховик ','Пуховик Пуховик Пуховик ',54,1,1,4);
GO


INSERT INTO users (login, password)
VALUES   ('admin', '$2y$12$fJP5jXFNe6ts6QrNlDBKqOWqilv.1Pbv/JPMFrvtdSP/T/OYnIzma'),
        ('guest', '$2y$12$fJP5jXFNe6ts6QrNlDBKqOWqilv.1Pbv/JPMFrvtdSP/T/OYnIzma');
GO

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('DELETE_USERS_PERMISSION');
GO

INSERT INTO users_roles(user_id, role_id)
SELECT (SELECT id FROM users WHERE login = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM users WHERE login = 'guest'), (SELECT id FROM roles WHERE name = 'ROLE_USER');
GO

CREATE TABLE users
(
    id       bigserial PRIMARY KEY,
    login    varchar(30) NOT NULL,
    password varchar(80) NOT NULL,
    email    varchar(50) UNIQUE
);
GO

CREATE TABLE roles
(
    id   serial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE
);
GO

CREATE TABLE users_roles
(
    user_id bigint NOT NULL,
    role_id int    NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) references users (id),
    FOREIGN KEY (role_id) references roles (id)
);
GO

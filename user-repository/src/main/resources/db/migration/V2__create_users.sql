DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id                    SERIAL PRIMARY KEY,
    email                      VARCHAR(45) UNIQUE,
    password                   VARCHAR(64) NOT NULL,
    first_name                 VARCHAR(20) NOT NULL,
    last_name                  VARCHAR(20) NOT NULL,
    role                       SERIAL      NOT NULL,
    is_account_non_expired     BOOLEAN DEFAULT FALSE,
    is_account_non_locked      BOOLEAN DEFAULT FALSE,
    is_credentials_non_expired BOOLEAN DEFAULT FALSE,
    is_enabled                 BOOLEAN DEFAULT FALSE,
    CONSTRAINT role_fk1 FOREIGN KEY (role) REFERENCES roles (role_id)
);
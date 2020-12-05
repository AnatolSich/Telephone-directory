DROP TABLE IF EXISTS phone_numbers;
DROP TABLE IF EXISTS user_accounts;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS phone_companies;

CREATE TABLE users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL
);

CREATE TABLE phone_companies
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    code VARCHAR(250) NOT NULL
);

CREATE TABLE user_accounts
(
    user_id    int                 not null,
    company_id int                 not null,
    amount     decimal default 0.0 not null,
    constraint account_id PRIMARY KEY (user_id, company_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES phone_companies (id) ON DELETE CASCADE
);

CREATE TABLE phone_numbers
(
    number     VARCHAR(250) PRIMARY KEY,
    user_id    int not null,
    company_id int not null,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES phone_companies (id) ON DELETE CASCADE
);


INSERT INTO users (first_name, last_name)
VALUES ('Aliko', 'Dangote'),
       ('Bill', 'Gates'),
       ('Folrunsho', 'Alakija');

INSERT INTO phone_companies (name, code)
VALUES ('LifeCell', '063'),
       ('Vodafone', '066'),
       ('KyivStar', '096');

INSERT INTO phone_numbers (number, user_id, company_id)
VALUES ('4582358', 1, 2),
       ('6842367', 2, 3),
       ('4695821', 2, 1),
       ('2573642', 3, 1);

INSERT INTO user_accounts (user_id, company_id, amount)
VALUES (1, 2, 100),
       (2, 3, 200),
       (2, 1, 300),
       (3, 1, 400);

/*
select *
from user_accounts
where  user_accounts.user_id */

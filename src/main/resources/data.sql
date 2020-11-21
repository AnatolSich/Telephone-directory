DROP TABLE IF EXISTS phone_numbers;
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

CREATE TABLE phone_numbers
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    number      VARCHAR(250) NOT NULL,
    userId    int          not null,
    companyId int          not null,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (companyId) REFERENCES phone_companies (id) ON DELETE CASCADE
);



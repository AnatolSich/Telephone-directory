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
    user_id    int          not null,
    company_id int          not null,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES phone_companies (id) ON DELETE CASCADE
);

INSERT INTO users (first_name, last_name) VALUES
('Aliko', 'Dangote'),
('Bill', 'Gates'),
('Folrunsho', 'Alakija');

INSERT INTO phone_companies (name, code) VALUES
('LifeCell', '063'),
('Vodafone', '066'),
('KyivStar', '096');

INSERT INTO phone_numbers (number, user_id, company_id) VALUES
('4582358', 1, 2),
('6842367', 2, 3),
('4695821', 2, 1),
('2573642', 3, 1);
DROP TABLE IF EXISTS employee;

CREATE TABLE employee(
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    employee_id VARCHAR (40),
    login_name VARCHAR (40),
    name VARCHAR (40),
    salary BIGINT
);
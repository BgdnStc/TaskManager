-- ALTER USER bgdn WITH PASSWORD 'password';

CREATE TABLE IF NOT EXISTS Tasks (
    id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    due_date TIMESTAMP NOT NULL,
    person_assigned VARCHAR(100) NOT NULL,
    comment VARCHAR(500),
    PRIMARY KEY (id)
);

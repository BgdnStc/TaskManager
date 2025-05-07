CREATE TABLE IF NOT EXISTS Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(500),
    department VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS Comments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(500),
    author INT NOT NULL,
    FOREIGN KEY (author) REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS Tasks (
     id INT PRIMARY KEY AUTO_INCREMENT,
     parent_task_id INT NULL,
     title VARCHAR(100) NOT NULL,
     description VARCHAR(500),
     due_date TIMESTAMP NOT NULL,
     person_assigned INT NOT NULL,
     comment INT NULL,
     FOREIGN KEY (parent_task_id) REFERENCES Tasks(id),
     FOREIGN KEY (person_assigned) REFERENCES Users(id),
     FOREIGN KEY (comment) REFERENCES Comments(id)
);

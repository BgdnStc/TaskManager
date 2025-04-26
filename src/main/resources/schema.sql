CREATE TABLE IF NOT EXISTS Tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    parent_task_id INT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    due_date TIMESTAMP NOT NULL,
    person_assigned VARCHAR(100) NOT NULL,
    comment VARCHAR(500),
    FOREIGN KEY (parent_task_id) REFERENCES Tasks(id)
);

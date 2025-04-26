package com.github.bgdnstc.taskmanager;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
    private final JdbcClient jdbcClient;

    public TaskRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Task> getTasks() {
        return jdbcClient.sql("select * from Tasks;").query(Task.class).list();
    }

    public Task getTaskById(Integer id) {
        return jdbcClient.sql("select * from tasks where id = :id")
                .param("id", id).query(Task.class).single();
    }

    public void insertTask(Task task) {
        jdbcClient.sql("insert into tasks(title, description, due_Date, person_assigned, comment) values(?,?,?,?,?)")
                .params(List.of(task.title(), task.description(), task.dueDate(), task.personAssigned(), task.comment()))
                .update();
    }

    public void updateTask(Task task) {
        //TODO
    }

    public void deleteTaskById(Integer id) {
        jdbcClient.sql("delete from tasks where id = :id").param("id", id).update();
    }
}

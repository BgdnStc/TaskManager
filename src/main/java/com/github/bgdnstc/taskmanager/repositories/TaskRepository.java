package com.github.bgdnstc.taskmanager.repositories;

import com.github.bgdnstc.taskmanager.records.Task;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
    private final JdbcClient jdbcClient;

    // controller constructor; behaviour given by the JdbcClient class with dependency injection
    public TaskRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // query all tasks and list them
    public List<Task> getTasks() {
        return jdbcClient.sql("select * from tasks;").query(Task.class).list();
    }

    // query one task by id
    public Task getTaskById(Integer id) {
        return jdbcClient.sql("select * from tasks where id = :id;").param("id", id).query(Task.class).single();
    }

    // insert a task in the tasks table, the task object is translated into a database record
    // the inserted record can be either a task or a subtask, depending on the parentTaskId attribute
    public void insertTask(Task task) {
        if (task.parentTaskId() != null) {
            jdbcClient.sql("insert into tasks(parent_task_id, title, description, due_Date, person_assigned) values(?,?,?,?,?);").params(List.of(task.parentTaskId(), task.title(), task.description(), task.dueDate(), task.personAssigned())).update();
        } else {
            jdbcClient.sql("insert into tasks(parent_task_id, title, description, due_Date, person_assigned) values(NULL,?,?,?,?);").params(List.of(task.title(), task.description(), task.dueDate(), task.personAssigned())).update();
        }
    }

    // update a record
    // option to only add a comment to an existing task or subtask
    public void updateTask(Task task, Integer id) {

        if (task.parentTaskId() != null) {
            jdbcClient.sql("update tasks set parent_task_id = ?, title = ?, description = ?, due_Date = ?, person_assigned = ? where id = ?;").params(List.of(task.parentTaskId(), task.title(), task.description(), task.dueDate(), task.personAssigned(), id)).update();
        } else {
            if (task.title() == null && task.description() == null && task.dueDate() == null && task.personAssigned() == null && task.comment() != null) {
                jdbcClient.sql("update tasks set comment = ? where id = ?;").params(List.of(task.comment(), task.id())).update();
            } else {
                jdbcClient.sql("update tasks set title = ?, description = ?, due_Date = ? where id = ?;").params(List.of(task.title(), task.description(), task.dueDate(), task.id())).update();
            }
        }
    }

    // remove one record from the database by their id
    public void deleteTaskById(Integer id) {
        jdbcClient.sql("delete from tasks where id = :id;").param("id", id).update();
    }

    // query the tasks records and order them alphabetically by title
    public List<Task> getTasksByTitle() {
        return jdbcClient.sql("select * from tasks order by title asc;").query(Task.class).list();
    }
}

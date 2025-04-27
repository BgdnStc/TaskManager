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
        return jdbcClient.sql("select * from Tasks;")
                .query(Task.class)
                .list();
    }

    public Task getTaskById(Integer id) {
        return jdbcClient.sql("select * from tasks where id = :id")
                .param("id", id)
                .query(Task.class)
                .single();
    }

    public void insertTask(Task task) {
        if (task.parentTaskId() != null) {
            jdbcClient.sql("insert into tasks(parent_task_id, title, description, due_Date, person_assigned, comment) values(?,?,?,?,?,?)")
                    .params(List.of(task.parentTaskId(), task.title(), task.description(), task.dueDate(), task.personAssigned(), task.comment()))
                    .update();
        } else {
            jdbcClient.sql("insert into tasks(parent_task_id, title, description, due_Date, person_assigned, comment) values(NULL,?,?,?,?,?)")
                    .params(List.of(task.title(), task.description(), task.dueDate(), task.personAssigned(), task.comment()))
                    .update();
        }
    }

    public void updateTask(Task task, Integer id) {

        if (task.parentTaskId() != null) {
            jdbcClient.sql("update tasks set parent_task_id = ?, title = ?, description = ?, due_Date = ?, person_assigned = ? where id = ?")
                    .params(List.of(task.parentTaskId(), task.title(), task.description(), task.dueDate(), task.personAssigned(), id))
                    .update();
        } else {
            if (task.title() == null && task.description() == null && task.dueDate() == null && task.personAssigned() == null && task.comment() != null) {
                jdbcClient.sql("update tasks set comment = ? where id = ?")
                        .params(List.of(task.comment(), task.id()))
                        .update();
            } else {
                jdbcClient.sql("update tasks set title = ?, description = ?, due_Date = ? where id = ?")
                        .params(List.of(task.title(), task.description(), task.dueDate(), task.id()))
                        .update();
            }
        }
    }

//    public void updateSubtask(Task task, Integer id) {
//        jdbcClient.sql("update tasks set parent_task_id = ? where id = ?")
//                .params(List.of(task.parentTaskId(), id))
//                .update();
//    }
    public void deleteTaskById(Integer id) {
        jdbcClient.sql("delete from tasks where id = :id")
                .param("id", id)
                .update();
    }

    public List<Task> getTasksByTitle() {
        return jdbcClient.sql("select * from tasks order by title asc")
                .query(Task.class)
                .list();
    }
}

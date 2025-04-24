package com.github.bgdnstc.taskmanager;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
//    private static final Logger log = LoggerFactory.getLogger(TaskRepository.class);

    private final JdbcClient jdbcClient;


    public TaskRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Task> getTasks() {
        return jdbcClient.sql("select * from Tasks;").query(Task.class).list();
    }

    public Task getTaskById(Integer id) {
        return jdbcClient.sql("select id, title, description, due_Date, person_assigned, comment from tasks where id = :id")
                .param("id", id).query(Task.class).single();
    }

    public void insertTask(Task task) {
        jdbcClient.sql("insert into tasks(id, title, description, due_Date, person_assigned, comment) values(?,?,?,?,?,?)")
                .params(List.of(task.id(), task.title(), task.description(), task.dueDate(), task.personAssigned(), task.comment()))
                .update();
    }

//    public void updateTask(Task task) {}

    public void deleteTaskById(Integer id) {
        jdbcClient.sql("delete from tasks where id = :id").param("id", id).update();
    }

    public int count() {
        return jdbcClient.sql("select * from tasks").query().listOfRows().size();
    }

    public void saveAll(List<Task> tasks) {
        tasks.stream().forEach(this::insertTask);
    }
}

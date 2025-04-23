package com.github.bgdnstc.taskmanager;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<Task>();

    public List<Task> getAll() {
        return tasks;
    }

    public Task getTaskById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    void updateTask(Task task) {
        tasks.set(tasks.indexOf(task), task);
    }

    void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    @PostConstruct
    public void init() {
        tasks.add(new Task(1, "T1", "job1", new Date(2025), "John", "do it"));
        tasks.add(new Task(2, "T2", "job2", new Date(2025), "Johnny", "- - -"));
    }
}

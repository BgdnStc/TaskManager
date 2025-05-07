package com.github.bgdnstc.taskmanager.controllers;

import com.github.bgdnstc.taskmanager.records.Task;
import com.github.bgdnstc.taskmanager.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    // controller constructor, behaviour given by the TaskRepository class
    TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // get all tasks from the database and list them
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    // retrieve one record from the tasks table
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    Task getTask(@PathVariable Integer id) {
        Task task = taskRepository.getTaskById(id);
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return task;
        }
    }

    // insert one record in the database
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void addTask(@RequestBody Task task) {
        taskRepository.insertTask(task);
    }

    // update one record
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    void updateTask(@PathVariable int id, @RequestBody Task task) {
        taskRepository.updateTask(task, id);
    }

    // delete one record
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable int id) {
        taskRepository.deleteTaskById(id);
    }

    // retrieve all tasks from the database and order them by title
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/title")
    List<Task> getAllByTitle() {
        return taskRepository.getTasksByTitle();
    }
}

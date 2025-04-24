package com.github.bgdnstc.taskmanager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("")
    List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    @GetMapping("/{id}")
    Task getTask(@PathVariable Integer id) {
        Task task = taskRepository.getTaskById(id);
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else {
            return task;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void addTask(@RequestBody Task task) {
        taskRepository.insertTask(task);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    void updateTask(@PathVariable int id, @RequestBody Task task) {
        taskRepository.updateTask(task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable int id) {
        taskRepository.deleteTaskById(id);
    }
}

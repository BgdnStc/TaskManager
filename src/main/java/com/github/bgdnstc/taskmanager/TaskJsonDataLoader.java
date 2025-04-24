package com.github.bgdnstc.taskmanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class TaskJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(TaskJsonDataLoader.class);
    private final TaskRepository taskRepository;
    private final ObjectMapper objectMapper;

    public TaskJsonDataLoader(TaskRepository taskRepository, ObjectMapper objectMapper) {
        this.taskRepository = taskRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(taskRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Tasks allTasks = objectMapper.readValue(inputStream, Tasks.class);
                log.info("Reading {} runs from JSON data and saving to in-memory collection.", allTasks.tasks().size());
                taskRepository.saveAll(allTasks.tasks());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Runs from JSON data because the collection contains data.");
        }
    }
}

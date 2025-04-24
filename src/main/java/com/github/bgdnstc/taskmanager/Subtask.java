package com.github.bgdnstc.taskmanager;

import java.util.Date;

public record Subtask() {
    static Integer id;
    static Integer taskId;
    static String title;
    static String description;
    static Date dueDate;
}

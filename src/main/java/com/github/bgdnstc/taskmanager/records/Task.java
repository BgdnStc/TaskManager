package com.github.bgdnstc.taskmanager.records;

import java.util.Date;

public record Task(
        Integer id,
        Integer parentTaskId,
        String title,
        String description,
        Date dueDate,
        String personAssigned,
        String comment
) {
}

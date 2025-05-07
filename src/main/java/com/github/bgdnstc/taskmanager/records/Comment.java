package com.github.bgdnstc.taskmanager.records;

public record Comment(
        Integer id,
        String content,
        Integer author
) {
}

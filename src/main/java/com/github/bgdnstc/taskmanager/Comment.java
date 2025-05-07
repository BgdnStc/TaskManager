package com.github.bgdnstc.taskmanager;

public record Comment(
        Integer id,
        String content,
        Integer author
) {
}

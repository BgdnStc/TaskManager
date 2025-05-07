package com.github.bgdnstc.taskmanager;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    private final JdbcClient jdbcClient;

    public CommentRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Comment> getComments() {
        return jdbcClient.sql("select * from comments").query(Comment.class).list();
    }

    public Comment getComment(int id) {
        return jdbcClient.sql("select * from comments where id = :id").param("id", id).query(Comment.class).single();
    }

    public void insertComment(Comment comment) {
        jdbcClient.sql("insert into comments(content, author) values (?,?)").params(List.of(comment.content(), comment.author())).update();
    }

    public void deleteComment(int id) {
        jdbcClient.sql("delete from comments where id = :id").param("id", id).update();
    }
}

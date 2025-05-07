package com.github.bgdnstc.taskmanager.repositories;

import com.github.bgdnstc.taskmanager.records.Comment;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    private final JdbcClient jdbcClient;

    public CommentRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // query all comments and list them
    public List<Comment> getComments() {
        return jdbcClient.sql("select * from comments").query(Comment.class).list();
    }

    // query one comment by id
    public Comment getComment(int id) {
        return jdbcClient.sql("select * from comments where id = :id").param("id", id).query(Comment.class).single();
    }

    // insert a comment in the comments table
    public void insertComment(Comment comment) {
        jdbcClient.sql("insert into comments(content, author) values (?,?)").params(List.of(comment.content(), comment.author())).update();
    }

    // remove one record from the database by their id
    public void deleteComment(int id) {
        jdbcClient.sql("delete from comments where id = :id").param("id", id).update();
    }
}

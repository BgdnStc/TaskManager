package com.github.bgdnstc.taskmanager.controllers;

import com.github.bgdnstc.taskmanager.records.Comment;
import com.github.bgdnstc.taskmanager.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentRepository commentRepository;

    CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // get all comments from the database and list them
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<Comment> getComments() {
        return commentRepository.getComments();
    }

    // retrieve one record from the comments table
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    Comment getComment(@PathVariable int id) {
        return commentRepository.getComment(id);
    }

    // insert one record in the database
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void addComment(@RequestBody Comment comment) {
        commentRepository.insertComment(comment);
    }

    // delete one record
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable int id) {
        commentRepository.deleteComment(id);
    }
}

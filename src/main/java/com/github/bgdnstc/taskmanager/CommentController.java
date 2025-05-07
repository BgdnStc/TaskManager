package com.github.bgdnstc.taskmanager;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<Comment> getComments() {
        return commentRepository.getComments();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    Comment getComment(@PathVariable int id) {
        return commentRepository.getComment(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createComment(@RequestBody Comment comment) {
        commentRepository.insertComment(comment);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable int id) {
        commentRepository.deleteComment(id);
    }
}

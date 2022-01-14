package com.socialapp.app.controller;

import com.socialapp.app.model.Comment;
import com.socialapp.app.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable String id) {
        Comment comment = commentService.getComment(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> allComments = commentService.getAllComments();
        return ResponseEntity.ok(allComments);
    }

    @GetMapping("/by-task")
    public ResponseEntity<List<Comment>> getAllCommentsByTask(@RequestParam String taskId) {
        List<Comment> comments = commentService.getAllCommentsByTask(taskId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addComment(@RequestParam String username, @RequestParam String taskId,
                                           @RequestBody @Valid Comment comment) {
        commentService.addComment(username, taskId, comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@RequestBody @Valid Comment comment, @PathVariable String id) {
        commentService.updateComment(comment, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

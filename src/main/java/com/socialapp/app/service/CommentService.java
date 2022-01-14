package com.socialapp.app.service;

import com.socialapp.app.model.Comment;
import com.socialapp.app.model.Task;
import com.socialapp.app.model.User;
import com.socialapp.app.repository.CommentRepository;
import com.socialapp.app.repository.TaskRepository;
import com.socialapp.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private TaskRepository taskRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public CommentService(TaskRepository taskRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public void addComment(String username, String taskId, Comment comment) {
        User user = userRepository.findByUsername(username).get();
        Task task = taskRepository.findById(taskId).get();
        comment.setTask(task);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    public Comment getComment(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalStateException("No such comment"));
    }

    public List<Comment> getAllCommentsByTask(String taskId) {
        return commentRepository.findAllByTaskId(taskId);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    //TODO: IT'S SUPPOSED TO BE A DTO
    public void updateComment(Comment commentToUpdate, String id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalStateException("No task with given id"));
        comment.setDescription(commentToUpdate.getDescription());
        commentRepository.save(comment);
    }

    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }
}

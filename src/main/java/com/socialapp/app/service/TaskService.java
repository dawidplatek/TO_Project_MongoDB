package com.socialapp.app.service;

import com.socialapp.app.model.Task;
import com.socialapp.app.model.User;
import com.socialapp.app.repository.TaskRepository;
import com.socialapp.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void addTask(String username, Task task) {
        User user = userRepository.findByUsername(username).get();
        task.setUser(user);
        taskRepository.save(task);
    }

    public Task getTask(String id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("No task with given id"));
    }

    public List<Task> getTasksByUserId(String userId) {
        return taskRepository.findAllByUserId(userId);
    }

    public List<Task> getTaskByStatus(boolean done) {
        return taskRepository.findByDoneStatus(done);
    }

    public Task getTaskByDescription(String description) {
        return taskRepository.findByDescription(description).orElseThrow(() -> new IllegalStateException("No task with given description"));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
//TODO: IT'S SUPPOSED TO BE A DTO
    public void updateTask(Task taskToUpdate, String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("No task with given id"));
        task.setDescription(taskToUpdate.getDescription());
        task.setDone(taskToUpdate.isDone());
        taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}

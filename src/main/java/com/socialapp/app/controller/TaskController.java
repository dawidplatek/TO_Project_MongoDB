package com.socialapp.app.controller;

import com.socialapp.app.model.Task;
import com.socialapp.app.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        Task task = taskService.getTask(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/by-userid")
        public ResponseEntity<List<Task>> getAllTasksByUserId(@RequestParam String id) {
            List<Task> allTasks = taskService.getTasksByUserId(id);
            return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<Task>> getAllTasksByStatus(@RequestParam boolean status) {
        List<Task> tasks = taskService.getTaskByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/by-description")
    public ResponseEntity<Task> getAllTasksByDescription(@RequestParam String description) {
        Task task = taskService.getTaskByDescription(description);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addTask(@RequestParam String username, @RequestBody @Valid Task task) {
        taskService.addTask(username, task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@RequestBody @Valid Task taskToUpdate, @PathVariable String id) {
        taskService.updateTask(taskToUpdate, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

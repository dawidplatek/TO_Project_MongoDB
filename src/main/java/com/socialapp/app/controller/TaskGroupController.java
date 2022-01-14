package com.socialapp.app.controller;

import com.socialapp.app.model.TaskGroup;
import com.socialapp.app.service.TaskGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/taskgroups")
public class TaskGroupController {

    private TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupService taskGroupService) {
        this.taskGroupService = taskGroupService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getTaskGroup(@PathVariable String id) {
        TaskGroup taskGroup = taskGroupService.getTaskGroup(id);
        return ResponseEntity.ok(taskGroup);
    }

    @GetMapping
    public ResponseEntity<List<TaskGroup>> getAllTaskGroups() {
        List<TaskGroup> allTasksGroups = taskGroupService.getAllTasksGroups();
        return ResponseEntity.ok(allTasksGroups);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addTaskGroup(@RequestParam String username, @RequestBody @Valid TaskGroup taskGroup) {
        taskGroupService.addTaskGroup(username, taskGroup);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTaskGroup(@RequestBody @Valid TaskGroup taskGroup, @PathVariable String id) {
        taskGroupService.updateTaskGroup(taskGroup, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable String id) {
        taskGroupService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

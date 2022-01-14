package com.socialapp.app.service;

import com.socialapp.app.model.Task;
import com.socialapp.app.model.TaskGroup;
import com.socialapp.app.model.User;
import com.socialapp.app.repository.TaskGroupRepository;
import com.socialapp.app.repository.TaskRepository;
import com.socialapp.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskGroupService {

    private UserRepository userRepository;
    private TaskGroupRepository taskGroupRepository;
    private TaskRepository taskRepository;

    public TaskGroupService(UserRepository userRepository, TaskGroupRepository taskGroupRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskRepository = taskRepository;
    }

    public void addTaskGroup(String username, TaskGroup taskGroup) {
        User user = userRepository.findByUsername(username).get();
        taskGroup.setUser(user);
        taskGroupRepository.save(taskGroup);
    }
    public TaskGroup getTaskGroup(String id) {
        return taskGroupRepository.findById(id).orElseThrow(() -> new IllegalStateException("No task group with given id"));
    }


    public List<TaskGroup> getAllTasksGroups() {
        return taskGroupRepository.findAll();
    }

    //TODO: IT'S SUPPOSED TO BE A DTO
    public void updateTaskGroup(TaskGroup taskGroupToUpdate, String id) {
        TaskGroup taskGroup = taskGroupRepository.findById(id).orElseThrow(() -> new IllegalStateException("No task group with given id"));
        taskGroup.setDescription(taskGroupToUpdate.getDescription());
        taskGroup.setDone(taskGroupToUpdate.isDone());
        taskGroupRepository.save(taskGroup);
    }

    public void deleteTask(String id) {
        taskGroupRepository.deleteById(id);
    }
}

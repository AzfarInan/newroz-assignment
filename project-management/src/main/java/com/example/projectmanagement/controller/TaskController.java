package com.example.projectmanagement.controller;

import com.example.projectmanagement.entity.TaskEntity;
import com.example.projectmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    private Map<String, String> responseMap;

    @GetMapping("{projectId}/task")
    public List<TaskEntity> viewTasksByProjectId(@PathVariable String projectId){
        return taskService.viewTask(projectId);
    }

    @PostMapping("task/{projectId}")
    public Map<String, String> addNewTask(@PathVariable String projectId, @RequestBody TaskEntity task){
        responseMap = taskService.addNewTask(projectId, task);
        return responseMap;
    }

    @PutMapping("/task/{taskId}")
    public Map<String, String> updateTask(@PathVariable String taskId, @RequestBody TaskEntity taskEntity){
        responseMap = taskService.updateTask(taskId, taskEntity);
        return responseMap;
    }

    @DeleteMapping("/task/{taskId}")
    public Map<String, String> deleteTask(@PathVariable String taskId){
        responseMap = taskService.deleteTask(taskId);
        return responseMap;
    }
}

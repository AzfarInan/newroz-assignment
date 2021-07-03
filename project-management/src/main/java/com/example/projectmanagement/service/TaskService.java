package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.Statuses;
import com.example.projectmanagement.entity.ProjectEntity;
import com.example.projectmanagement.entity.TaskEntity;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private List<Statuses> statuses = Arrays.asList(
            new Statuses("Created"),
            new Statuses("Cancelled"),
            new Statuses("InProgress"),
            new Statuses("Completed"),
            new Statuses("OnHold")
    );

    public List<TaskEntity> viewTask(String projectId){
        return taskRepository.findByProjectId(projectId);
    }

    public Map<String, String> addNewTask(String projectID, TaskEntity task){
        Map<String, String> response = new HashMap<String, String>();
        try {
            ProjectEntity projectEntity = projectRepository.findByProjectId(projectID);
            if (projectEntity != null){
                TaskEntity taskEntity = taskRepository.findByTaskName(task.getTaskName());
                if (taskEntity != null)
                    throw new ResponseStatusException(NOT_ACCEPTABLE, "Task Already exists");

                if(!statuses.stream().filter(s -> s.getStatus().equals(task.getStatus())).findFirst().isPresent()){
                    throw new ResponseStatusException(NOT_ACCEPTABLE, "Status not found");
                }

                task.setTaskId(UUID.randomUUID().toString());
                task.setProjectId(projectID);
                taskRepository.save(task);
                response.put("userMessage", "Successfully added new task");
                return response;
            } else {
                throw new ResponseStatusException(NOT_ACCEPTABLE, "Project not found");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "Unable to add project");
        }
    }

    public Map<String, String> updateTask(String taskId, TaskEntity taskEntity){
        Map<String, String> response = new HashMap<String, String>();
        try {
            ProjectEntity projectEntity = projectRepository.findByProjectId(taskEntity.getProjectId());
            if(projectEntity != null){
                if(!statuses.stream().filter(s -> s.getStatus().equals(taskEntity.getStatus())).findFirst().isPresent()){
                    throw new ResponseStatusException(NOT_ACCEPTABLE, "Status not found");
                }
                taskEntity.setTaskId(taskId);
                taskRepository.save(taskEntity);
                response.put("userMessage", "Successfully updated task");
                return response;
            }
            else
                throw new ResponseStatusException(NOT_ACCEPTABLE, "Unable to update task");
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "Unable to update task");
        }
    }

    public Map<String, String> deleteTask(String taskId){
        Map<String, String> response = new HashMap<String, String>();
        try {
            taskRepository.deleteById(taskId);
            response.put("userMessage", "Successfully deleted task");
            return response;
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }
}

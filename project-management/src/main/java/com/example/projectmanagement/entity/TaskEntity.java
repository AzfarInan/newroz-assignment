package com.example.projectmanagement.entity;

import com.example.projectmanagement.repository.ProjectRepository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class TaskEntity {
    @Id
    private String taskId;
    private String taskName;
    private String status;
    private String projectId;

    public TaskEntity(){}

    public TaskEntity(String taskName, String status, String projectId){
        taskId = UUID.randomUUID().toString();
        this.taskName = taskName;
        this.status = status;
        this.projectId = projectId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getTaskName() {
        return taskName;
    }
}

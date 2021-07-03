package com.example.projectmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class ProjectEntity {
    @Id
    private String projectId;
    private String projectName;
    private String projectDesc;
    private String status;


    public ProjectEntity(){}

    public ProjectEntity(String projectName, String projectDesc, String status){
        this.projectId = UUID.randomUUID().toString();
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.status = status;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.Statuses;
import com.example.projectmanagement.entity.ProjectEntity;
import com.example.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    private Map<String, String> responseMap;

    @GetMapping("/projects")
    public List<ProjectEntity> getAllProjects(){
        return projectService.getAllList();
    }

    @GetMapping("/status")
    public List<Statuses> getStatus(){
        return projectService.getStatus();
    }

    @GetMapping("/projects/{projectId}")
    public ProjectEntity getByProjectId(@PathVariable String projectId){
        return projectService.getById(projectId);
    }

    @PostMapping("/project")
    public Map<String, String> addNewProject(@RequestBody ProjectEntity project){
        responseMap = projectService.addNewProject(project);
        return responseMap;
    }

    @PutMapping("/projects/{projectId}")
    public Map<String, String> updateProject(@PathVariable String projectId, @RequestBody ProjectEntity project){
        responseMap = projectService.updateProject(projectId, project);
        return responseMap;
    }

    @DeleteMapping("/projects/{projectId}")
    public Map<String, String> deleteProject(@PathVariable String projectId){
        responseMap = projectService.deleteProject(projectId);
        return responseMap;
    }

}

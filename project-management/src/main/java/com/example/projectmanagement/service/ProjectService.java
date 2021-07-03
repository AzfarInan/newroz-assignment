package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.Statuses;
import com.example.projectmanagement.entity.ProjectEntity;
import com.example.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private List<Statuses> statuses = Arrays.asList(
            new Statuses("Created"),
            new Statuses("Cancelled"),
            new Statuses("InProgress"),
            new Statuses("Completed"),
            new Statuses("OnHold")
    );

    public List<ProjectEntity> getAllList(){
        List<ProjectEntity> projects = new ArrayList<>();
        projectRepository.findAll()
                .forEach(projects::add);
        return projects;
    }

    public List<Statuses> getStatus(){
        return statuses;
    }

    public ProjectEntity getById(String projectId){
        try {
            ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
            if (projectEntity == null)
                throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

            return projectRepository.findByProjectId(projectId);
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }

    public Map<String, String> addNewProject(ProjectEntity project){
        Map<String, String> response = new HashMap<String, String>();
        try {
            ProjectEntity projectEntity = projectRepository.findByProjectName(project.getProjectName());
            if (projectEntity != null)
                throw new ResponseStatusException(NOT_ACCEPTABLE, "Project Already exists");

            if(!statuses.stream().filter(s -> s.getStatus().equals(project.getStatus())).findFirst().isPresent()){
                throw new ResponseStatusException(NOT_ACCEPTABLE, "Status not found");
            }

            project.setProjectId(UUID.randomUUID().toString());
            projectRepository.save(project);
            response.put("userMessage", "Successfully added new project");
            return response;

        } catch (Exception e) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "Unable to add project");
        }
    }

    public Map<String, String> updateProject(String projectId, ProjectEntity project){
        Map<String, String> response = new HashMap<String, String>();
        try {
            if(!statuses.stream().filter(s -> s.getStatus().equals(project.getStatus())).findFirst().isPresent()){
                throw new ResponseStatusException(NOT_ACCEPTABLE, "Status not found");
            }
            project.setProjectId(projectId);
            projectRepository.save(project);
            response.put("userMessage", "Successfully updated project");
            return response;
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "Unable to add project");
        }
    }

    public Map<String, String> deleteProject(String projectId){
        Map<String, String> response = new HashMap<String, String>();
        try {
            projectRepository.deleteById(projectId);
            response.put("userMessage", "Successfully deleted project");
            return response;
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }
}

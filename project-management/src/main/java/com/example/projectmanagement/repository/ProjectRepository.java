package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<ProjectEntity, String> {
    ProjectEntity findByProjectId(String id);
    ProjectEntity findByProjectName(String name);

}

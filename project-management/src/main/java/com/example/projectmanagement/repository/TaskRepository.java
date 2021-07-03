package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskEntity, String> {
    TaskEntity findByTaskName(String name);
    List<TaskEntity> findByProjectId(String projectId);
}

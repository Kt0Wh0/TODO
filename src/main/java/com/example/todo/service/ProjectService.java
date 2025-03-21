package com.example.todo.service;

import com.example.todo.model.Project;
import com.example.todo.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    public Project updateProject(long id, Project updateProject) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(updateProject.getName());
        project.setDescription(updateProject.getDescription());
        project.setStartDate(updateProject.getStartDate());
        project.setEndDate(updateProject.getEndDate());

        return projectRepository.save(project);
    }

}

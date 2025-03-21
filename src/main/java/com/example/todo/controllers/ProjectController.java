package com.example.todo.controllers;

import com.example.todo.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.todo.service.ProjectService;
import java.util.List;


@RestController
@RequestMapping("/project")
@RequiredArgsConstructor

public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        System.out.println("ПРИИИИВЕЕЕЕТ GET");
        return projectService.getAllProject();
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject).getBody();
    }

    @DeleteMapping
    public void deleteProject(@RequestBody Project project) {
        projectService.deleteProject(project.getId());
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable long id, @RequestBody Project updateProject) {
        System.out.println("PUT");
        Project updated = projectService.updateProject(id, updateProject);
        return ResponseEntity.ok(updated).getBody();
    }


}

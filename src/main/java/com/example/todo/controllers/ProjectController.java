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
// аналогично
public class ProjectController {
    // аналогично
    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProject();
    }

    @PostMapping
    public ResponseEntity<String> createProject(@RequestBody Project project) {
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body("Проект создан");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProject(@RequestBody Project project) {
        projectService.deleteProject(project.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Проект удален");
    }

    //короче тут я не понял в чем разница, сверху я возвращаю именно ResponseEntity<String>, а снизу просто строку
    //зачем тогда надо, короче не совсем понял че за прикол

    @PutMapping("/{id}")
    public String updateProject(@PathVariable long id, @RequestBody Project updateProject) {
        Project updated = projectService.updateProject(id, updateProject);
        return ResponseEntity.status(HttpStatus.OK).body("Проект изменен").getBody();
    }


}

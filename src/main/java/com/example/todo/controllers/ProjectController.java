package com.example.todo.controllers;

import com.example.todo.dto.ProjectDTO;
import com.example.todo.dto.ProjectIdDTO;
import com.example.todo.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.todo.service.ProjectService;
import com.example.todo.dto.ProjectDTO;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    // аналогично
    private final ProjectService projectService;

    //все проекты юзера
    @GetMapping("/{id}")
    public List<ProjectIdDTO> getProjectById(@PathVariable Long id) {
        return projectService.getProjectsByPersonId(id);
    }

    //создать проект для юзера
    // TODO: DTO вместо Entity
    @PostMapping("/{id}")
    public ResponseEntity<ProjectDTO> createProject(@PathVariable long id, @RequestBody ProjectDTO projectDTO) {
        ProjectDTO projectDTO1 = projectService.createProject(projectDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDTO1);
    }

    //удалить проект
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.OK).body("Проект удален");
    }

    //короче тут я не понял в чем разница, сверху я возвращаю именно ResponseEntity<String>, а снизу просто строку
    //зачем тогда надо, короче не совсем понял че за прикол

    //обновить проект
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProject(@PathVariable long id, @RequestBody Project updateProject) {
        Project updated = projectService.updateProject(id, updateProject);
        return ResponseEntity.status(HttpStatus.OK).body("Проект изменен");
    }
}

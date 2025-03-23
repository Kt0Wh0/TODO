package com.example.todo.controllers;

import com.example.todo.dto.ProjectDTO;
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

    //все проекты
    // TODO: DTO вместо Entity
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProject();
    }

    //все проекты юзера
    // TODO: DTO вместо Entity
    @GetMapping("/{id}")
    public List<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectsByPersonId(id);
    }

    //создать проект для юзера
    // TODO: DTO вместо Entity
    @PostMapping("/{id}")
    public ResponseEntity<Project> createProject(@PathVariable long id, @RequestBody ProjectDTO projectDTO) {
        Project project = projectService.createProject(projectDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    //удалить проект
    @DeleteMapping
    public ResponseEntity<String> deleteProject(@RequestBody Project project) {
        projectService.deleteProject(project.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Проект удален");
    }

    //короче тут я не понял в чем разница, сверху я возвращаю именно ResponseEntity<String>, а снизу просто строку
    //зачем тогда надо, короче не совсем понял че за прикол

    //обновить проект
    // TODO: ну короче стрингу тоже не принято возвращать. Поэтому лучше сделать объект DTO, назовешь ProjectUpdateStatusDTO
    // TODO: в котором опишешь одно поле String status. И эту дтошку будешь возвращать. Так лучше, чтобы сервер не тупо строку возвращал, а json
    // TODO: аналогично ко всем остальным эндпоинтам, где возвращаешь примитивный тип, либо ResponseEntity<> от примитивного типа (см. метод выше)
    @PutMapping("/{id}")
    public String updateProject(@PathVariable long id, @RequestBody Project updateProject) {
        Project updated = projectService.updateProject(id, updateProject);
        return ResponseEntity.status(HttpStatus.OK).body("Проект изменен").getBody();
    }
}

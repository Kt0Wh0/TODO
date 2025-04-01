package com.example.todo.controllers;

import com.example.todo.dto.TaskDTO;
import com.example.todo.dto.TaskIdDTO;
import com.example.todo.model.Project;
import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{idProject}")
    public List<TaskIdDTO> getTask(@PathVariable long idProject) {
        return taskService.getTasksByProject(idProject);
    }

    @PostMapping("/{idProject}")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO, @PathVariable long idProject) {
        TaskDTO taskDTO1 = taskService.createTask(taskDTO, idProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDTO1);
    }

    @DeleteMapping("/{idTask}")
    public ResponseEntity<String> deleteTask(@PathVariable long idTask) {
        taskService.deleteTask(idTask);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
    }
}

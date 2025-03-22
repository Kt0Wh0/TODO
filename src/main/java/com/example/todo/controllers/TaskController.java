package com.example.todo.controllers;

import com.example.todo.dto.TaskDTO;
import com.example.todo.model.Project;
import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/{id}")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO, @PathVariable long id) {
        Task task = taskService.createTask(taskDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping("/{id}")
    public List<Task> getTask(@PathVariable long id) {
        return taskService.getTasksByProject(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
    }

}

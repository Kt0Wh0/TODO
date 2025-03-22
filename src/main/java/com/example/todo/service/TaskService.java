package com.example.todo.service;

import com.example.todo.dto.TaskDTO;
import com.example.todo.model.Project;
import com.example.todo.model.Task;
import com.example.todo.repository.ProjectRepository;
import com.example.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    public Task createTask(TaskDTO taskDTO, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setEndData(taskDTO.getEndData());
        task.setProject(project);

        return taskRepository.save(task);
    }

    public List<Task> getTasksByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return taskRepository.findByProject(project);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}

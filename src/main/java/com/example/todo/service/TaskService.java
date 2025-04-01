package com.example.todo.service;

import com.example.todo.dto.TaskDTO;
import com.example.todo.dto.TaskIdDTO;
import com.example.todo.model.Project;
import com.example.todo.model.Task;
import com.example.todo.repository.ProjectRepository;
import com.example.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    public List<TaskIdDTO> getTasksByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        List<Task> tasks = taskRepository.findByProject(project);
        if (tasks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<TaskIdDTO>  tasksIdDTO = new ArrayList<>();
        for ( int i = 0; i < tasks.size(); i++ ) {
            TaskIdDTO taskIdDTO = new TaskIdDTO();
            taskIdDTO.setId((tasks.get(i)).getId());
            taskIdDTO.setName((tasks.get(i)).getName());
            taskIdDTO.setDescription((tasks.get(i)).getDescription());
            taskIdDTO.setEndData((tasks.get(i)).getEndData());
            tasksIdDTO.add(taskIdDTO);
        }

        return tasksIdDTO;
    }

    public TaskDTO createTask(TaskDTO taskDTO, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setEndData(taskDTO.getEndData());
        task.setProject(project);
        taskRepository.save(task);
        return taskDTO;
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}

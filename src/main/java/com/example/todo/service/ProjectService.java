package com.example.todo.service;

import com.example.todo.dto.ProjectDTO;
import com.example.todo.model.Project;
import com.example.todo.repository.ProjectRepository;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskService taskService;

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
                // вооо, здесь уже обработка хоть какая-то есть, если сущность не будет найдена
                .orElseThrow(() -> new RuntimeException("Project not found"));


        project.setName(updateProject.getName());
        project.setDescription(updateProject.getDescription());
        project.setStartDate(updateProject.getStartDate());
        project.setEndDate(updateProject.getEndDate());

        return projectRepository.save(project);
    }

    public List<ProjectDTO> findAllByPersonId(Long personId) {
        List<Project> projectList = projectRepository.findAllByPersonId(personId);
        if (Objects.isNull(projectList) || projectList.isEmpty()) {
            throw new EntityNotFoundException("У пользователя нет проектов!");
        }
        return projectList
                .stream()
                .map(this::convertEntityToDTO)
                .toList();
    }

    private ProjectDTO convertEntityToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        // чтобы заполнить эти два поля, тебе нужно в taskService описать два метода
        // 1) который получает по id проекта количество всех задач
        // 2) который получает по id проекта количество выполненных задач
        // и затем отсюда тянешь эти два метода и проставляешь эти два поля
        projectDTO.setAllTaskCount(null);
        projectDTO.setResolvedTaskCount(null);
        return projectDTO;
    }

}

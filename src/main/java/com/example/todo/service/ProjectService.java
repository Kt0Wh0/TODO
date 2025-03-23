package com.example.todo.service;

import com.example.todo.dto.ProjectDTO;
import com.example.todo.model.Person;
import com.example.todo.model.Project;
import com.example.todo.repository.PersonRepository;
import com.example.todo.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;

    public List<Project> getAllProject() {
        return projectRepository.findAll();

    }

    public List<Project> getProjectsByPersonId(Long id) {
        Person person =personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        List<Project> projects = projectRepository.findByPerson(person);
        if (projects.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return projects;
    }

    public Project createProject(ProjectDTO projectDTO, Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setPerson(person);

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

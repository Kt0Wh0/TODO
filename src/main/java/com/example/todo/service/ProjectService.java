package com.example.todo.service;

import com.example.todo.dto.ProjectDTO;
import com.example.todo.dto.ProjectIdDTO;
import com.example.todo.model.Person;
import com.example.todo.model.Project;
import com.example.todo.repository.PersonRepository;
import com.example.todo.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;

    public List<ProjectIdDTO> getProjectsByPersonId(Long id) {
        Person person =personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person nott found"));

        List<Project> projects = projectRepository.findByPerson(person);
        if (projects.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<ProjectIdDTO> projectIdDTOS = new ArrayList<>();

        for (int i = 0; i < projects.size(); i ++) {
            ProjectIdDTO projectDTO = new ProjectIdDTO();
            projectDTO.setId((projects.get(i)).getId());
            projectDTO.setName((projects.get(i)).getName());
            projectDTO.setDescription((projects.get(i)).getDescription());
            projectDTO.setStartDate((projects.get(i)).getStartDate());
            projectDTO.setEndDate((projects.get(i)).getEndDate());
            System.out.println("НОМЕР " + i + " " + projectDTO);
            projectIdDTOS.add(projectDTO);
        }
        return projectIdDTOS;
    }

    public ProjectDTO createProject(ProjectDTO projectDTO, Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setPerson(person);
        projectRepository.save(project);

        return projectDTO;
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

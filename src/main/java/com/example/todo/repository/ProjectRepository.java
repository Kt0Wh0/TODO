package com.example.todo.repository;

import com.example.todo.model.Person;
import com.example.todo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByPerson(Person person);
}

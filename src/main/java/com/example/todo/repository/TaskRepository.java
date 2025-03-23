package com.example.todo.repository;

import com.example.todo.model.Project;
import com.example.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // TODO: аналогично. не уверен что будет работать. проверь если ок, то норм
    List<Task> findByProject(Project project);
}

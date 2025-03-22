package com.example.todo.repository;

import com.example.todo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = """
        select 
            p.*
        from 
            project p
        where 
            p.person_id = :personId
    """, nativeQuery = true)
    List<Project> findAllByPersonId(Long personId);
}

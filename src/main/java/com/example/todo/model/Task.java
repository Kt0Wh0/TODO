package com.example.todo.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "task")
@Getter
@Setter

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "end_data")
    private LocalDate endData;
    @Column(name = "is_resolved")
    private boolean isResolved;


    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)  // Внешний ключ
    private Project project;


}

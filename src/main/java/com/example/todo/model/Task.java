package com.example.todo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endData;
    @Column(name = "is_resolved")
    private boolean isResolved; // Boolean


    @ManyToOne // LAZY нужно
    @JoinColumn(name = "project_id", nullable = false)  // Внешний ключ
    private Project project;


}

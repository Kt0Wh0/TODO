package com.example.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="project")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ManyToOne // где fetchType.LAZY?
    @JoinColumn(name = "person_id", nullable = false)
    @JsonIgnore // эту хуйню убирай
    private Person person;

    // Зачем тебе это поле? personId достаешь из person.getId()
    @Transient
    private Long personId;

    @JsonProperty // эту хуйню убирай
    public void setPersonId(Long personId) {
        this.personId = personId;
        this.person = personId != null ? new Person(personId) : null;
    }
}

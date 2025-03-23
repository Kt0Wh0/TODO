package com.example.todo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "person")
@Getter
@Setter
@SequenceGenerator(name = "id_gen", sequenceName = "person_id_seq", allocationSize = 1)
public class Person {
    public Person(Long id) {
        this.id = id;
    }
    public Person() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "pass")
    private Integer pass;

}



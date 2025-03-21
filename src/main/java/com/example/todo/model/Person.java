package com.example.todo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "person")
@Getter
@Setter
public class Person {
    public Person(Long id) {
        this.id = id;
    }
    public Person() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "pass")
    private int pass;
}

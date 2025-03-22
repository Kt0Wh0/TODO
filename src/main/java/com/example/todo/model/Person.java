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
    private long id; // принято писать типы-обертки вместо примитивов, поэтому Long
    // потому что обертки могут принимать значение null, а long не может. Типо ты не знаешь, может быть когда-то где-то потом у тебя id Будет null принимать
    // и всё сломается тогда

    @Column(name = "name")
    private String name;
    @Column(name = "pass")
    private int pass; // Integer

    // для теста создал
    public Person(String name, int pass) {
        this.id = null; // видишь, ломается
    }
}



package com.example.todo.service;

import com.example.todo.model.Person;
import com.example.todo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PersonService {
    private PersonRepository personRepository;

    public Person register(Person person) {
        return personRepository.save(person);
    }

}

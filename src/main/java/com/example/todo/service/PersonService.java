package com.example.todo.service;

import com.example.todo.model.Person;
import com.example.todo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PersonService {
    // аналогично. почему private final?
    private final PersonRepository personRepository;

    public Person register(Person person) {
        return personRepository.save(person);
    }
    public Person login(String name, int pass) {
        // а что будет если такого юзера нет? Null просто вылетит? Если да, то ок пока что если нет, то напишу в телегу

        return personRepository.findByNameAndPass(name, pass);
    }

}

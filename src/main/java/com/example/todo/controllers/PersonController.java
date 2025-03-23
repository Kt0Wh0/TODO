package com.example.todo.controllers;

import com.example.todo.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.todo.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    // напиши объясни зачем private final пишешь? Это надо делать, но мне надо понять, понимаешь ли ты или нет
    private final PersonService personService;

    //регистр
    // TODO: запрещено возвращать и принимать Entity-классы, контроллер работает только с ДТОшками, поэтому
    // TODO: вместо Person - PersonDTO
    @PostMapping("/register")
    public Person create(@RequestBody Person person) {
        return ResponseEntity.ok(personService.register(person)).getBody();
        //return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь создан");
    }

    //логин
    // TODO: а где реализация? я даже если кину несущесвтующие name и pass, то меня пустит?
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String name, @RequestParam int pass) {
        // TODO: вот напиши personService.login(name, pass); чо забыл что ли метод вызвать?
        return ResponseEntity.status(HttpStatus.OK).body("Пользователь существует ы");
    }



}

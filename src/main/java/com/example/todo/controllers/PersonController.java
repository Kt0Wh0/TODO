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
// зачем пробел здесь?
public class PersonController {

    // напиши объясни зачем private final пишешь? Это надо делать, но мне надо понять, понимаешь ли ты или нет
    private final PersonService personService;

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody Person person) {
        //return ResponseEntity.ok(personService.register(person)).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь создан");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String name, @RequestParam int pass) {
        return ResponseEntity.status(HttpStatus.OK).body("Пользователь существует ы");
    }



}

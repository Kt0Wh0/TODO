package com.example.todo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// TODO: @Data содержит в себе @Setter и @Getter уже (и еще многое другое). Для дтошек хватит геттеров и сеттеров
@Data
@Getter
@Setter
public class PersonDTO {
    private String name;
    private int pass;
}

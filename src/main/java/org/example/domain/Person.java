package org.example.domain;

import lombok.Data;

@Data
public class Person {
    private String name;
    private String surname;
    private int age;

    public Person() {}

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}
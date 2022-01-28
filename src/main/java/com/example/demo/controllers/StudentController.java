package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @GetMapping
    public List<Student> getStudents() {
        return List.of(new Student(1L, "Gabriel", "gabriel@gmail.com", LocalDate.of(1997, 01, 03), 25));
    }
}

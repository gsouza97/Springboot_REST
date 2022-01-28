package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Student;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(new Student(1L, "Gabriel", "gabriel@gmail.com", LocalDate.of(1997, 01, 03), 25));
    }
}

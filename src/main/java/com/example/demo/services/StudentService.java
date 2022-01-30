package com.example.demo.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStundent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);

        if (exists) {
            studentRepository.deleteById(id);
        } else {
            throw new IllegalStateException("student with id" + id + "does not exists");
        }
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {

        // Verifica se tem o estudante com o id passado
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("student with id" + id + "does not exist"));

        // Se o nome não for nulo e maior do que 0 e se o nome passado não for igual ao
        // nome já armazenado, atualiza o nome
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        // Se o email não for nulo e maior do que 0 e se o email passado não for igual
        // ao
        // email já armazenado, atualiza o email
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {

            // Verifica se aquele email já foi utilizado
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}

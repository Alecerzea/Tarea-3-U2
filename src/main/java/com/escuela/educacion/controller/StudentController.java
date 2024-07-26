// src/main/java/com/escuela/educacion/controller/StudentController.java

package com.escuela.educacion.controller;

import com.escuela.educacion.model.Student;
import com.escuela.educacion.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return saveStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student existingStudent = studentService.getStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        existingStudent.setUser(student.getUser());
        existingStudent.setAcademicHistory(student.getAcademicHistory());
        return saveStudent(existingStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Student> saveStudent(Student student) {
        validateStudent(student);
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    private void validateStudent(Student student) {
        if (student.getUser() == null || student.getAcademicHistory() == null) {
            throw new InvalidStudentDataException("Invalid student data");
        }
    }
}

// Custom exception classes

@ResponseStatus(HttpStatus.NOT_FOUND)
class StudentNotFoundException extends ResponseStatusException {
    public StudentNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidStudentDataException extends ResponseStatusException {
    public InvalidStudentDataException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
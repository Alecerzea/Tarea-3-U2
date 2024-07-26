package com.escuela.educacion.controller;

import com.escuela.educacion.model.Student;
import com.escuela.educacion.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        validateStudent(student);
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student existingStudent = studentService.getStudentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        validateStudent(student);
        existingStudent.setUser(student.getUser());
        existingStudent.setAcademicHistory(student.getAcademicHistory());
        return studentService.saveStudent(existingStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    private void validateStudent(Student student) {
        // Add validation logic here, e.g., check for null or empty fields
        if (student.getUser() == null || student.getAcademicHistory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid student data");
        }
    }
}
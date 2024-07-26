package com.escuela.educacion.repository;

import com.escuela.educacion.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
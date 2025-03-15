package edu.miu.cs.cs425.lab12.repository;

import edu.miu.cs.cs425.lab12.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
package edu.miu.cs.cs425.lab12.service;

import edu.miu.cs.cs425.lab12.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long studentId); // Changed from Integer to Long
    Student addStudent(Student student);
    Student updateStudent(Long studentId, Student student); // Changed from Integer to Long
    void deleteStudent(Long studentId); // Changed from Integer to Long
}
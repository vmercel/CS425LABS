package edu.miu.cs.cs425.lab12.service.impl;

import edu.miu.cs.cs425.lab12.model.Student;
import edu.miu.cs.cs425.lab12.repository.StudentRepository;
import edu.miu.cs.cs425.lab12.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long studentId) { // Changed from Integer to Long
        return studentRepository.findById(studentId)
                .orElse(null);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long studentId, Student student) { // Changed from Integer to Long
        Student existing = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setStudentNumber(student.getStudentNumber());
        existing.setEmail(student.getEmail());
        existing.setAddress(student.getAddress());
        existing.setCgpa(student.getCgpa());
        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(Long studentId) { // Changed from Integer to Long
        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }
        studentRepository.deleteById(studentId);
    }
}
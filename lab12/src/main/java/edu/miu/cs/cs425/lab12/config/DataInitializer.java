package edu.miu.cs.cs425.lab12.config;

import edu.miu.cs.cs425.lab12.model.Role;
import edu.miu.cs.cs425.lab12.model.Student;
import edu.miu.cs.cs425.lab12.model.User;
import edu.miu.cs.cs425.lab12.repository.RoleRepository;
import edu.miu.cs.cs425.lab12.repository.StudentRepository;
import edu.miu.cs.cs425.lab12.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository,
                           RoleRepository roleRepository,
                           StudentRepository studentRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize Roles
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);

            Role teacherRole = new Role();
            teacherRole.setName("TEACHER");
            roleRepository.save(teacherRole);

            Role studentRole = new Role();
            studentRole.setName("STUDENT");
            roleRepository.save(studentRole);
        }

        // Initialize Users
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("ADMIN role not found"));
            Role teacherRole = roleRepository.findByName("TEACHER")
                    .orElseThrow(() -> new RuntimeException("TEACHER role not found"));
            Role studentRole = roleRepository.findByName("STUDENT")
                    .orElseThrow(() -> new RuntimeException("STUDENT role not found"));

            User admin = new User();
            admin.setEmail("ana.admin@eregistrar.com");
            admin.setFirstName("Ana");
            admin.setLastName("Admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Collections.singleton(adminRole));
            userRepository.save(admin);

            User teacher = new User();
            teacher.setEmail("bob.teacher@eregistrar.com");
            teacher.setFirstName("Bob");
            teacher.setLastName("Teacher");
            teacher.setPassword(passwordEncoder.encode("teacher123"));
            teacher.setRoles(Collections.singleton(teacherRole));
            userRepository.save(teacher);

            User student = new User();
            student.setEmail("clara.student@eregistrar.com");
            student.setFirstName("Clara");
            student.setLastName("Student");
            student.setPassword(passwordEncoder.encode("student123"));
            student.setRoles(Collections.singleton(studentRole));
            userRepository.save(student);
        }

        // Initialize Students
        if (studentRepository.count() == 0) {
            Student student1 = new Student();
            student1.setFirstName("John");
            student1.setLastName("Doe");
            student1.setStudentNumber("A12345");
            student1.setEmail("john.doe@example.com");
            student1.setAddress("123 Main St, Springfield");
            student1.setCgpa(3.8);
            studentRepository.save(student1);

            Student student2 = new Student();
            student2.setFirstName("Jane");
            student2.setLastName("Smith");
            student2.setStudentNumber("A12346");
            student2.setEmail("jane.smith@example.com");
            student2.setAddress("456 Oak Ave, Springfield");
            student2.setCgpa(3.9);
            studentRepository.save(student2);

            Student student3 = new Student();
            student3.setFirstName("Mike");
            student3.setLastName("Johnson");
            student3.setStudentNumber("A12347");
            student3.setEmail("mike.johnson@example.com");
            student3.setAddress("789 Pine Rd, Springfield");
            student3.setCgpa(3.5);
            studentRepository.save(student3);
        }
    }
}
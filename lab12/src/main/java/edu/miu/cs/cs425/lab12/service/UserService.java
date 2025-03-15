package edu.miu.cs.cs425.lab12.service;

import edu.miu.cs.cs425.lab12.dto.UserRegistrationRequest;
import edu.miu.cs.cs425.lab12.model.Role;
import edu.miu.cs.cs425.lab12.model.User;
import edu.miu.cs.cs425.lab12.repository.RoleRepository;
import edu.miu.cs.cs425.lab12.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationRequest request) {
        User user = new User(request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFirstName(),
                request.getLastName());

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + request.getRole()));
        user.addRole(role);

        return userRepository.save(user);
    }
}
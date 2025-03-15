package edu.miu.cs.cs425.lab12.repository;

import edu.miu.cs.cs425.lab12.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
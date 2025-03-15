package edu.miu.cs.cs425.lab12.repository;

import edu.miu.cs.cs425.lab12.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
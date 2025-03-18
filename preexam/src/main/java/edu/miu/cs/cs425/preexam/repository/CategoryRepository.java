package edu.miu.cs.cs425.preexam.repository;

import edu.miu.cs.cs425.preexam.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
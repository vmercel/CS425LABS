// edu.miu.cs.cs425.revision1.repository.CategoryRepository.java
package edu.miu.cs.cs425.revision1.repository;

import edu.miu.cs.cs425.revision1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
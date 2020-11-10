package ru.abtank.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abtank.persist.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

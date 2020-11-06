package ru.abtank.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abtank.persist.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}

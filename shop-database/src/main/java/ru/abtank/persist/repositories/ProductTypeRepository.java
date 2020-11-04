package ru.abtank.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abtank.persist.model.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}

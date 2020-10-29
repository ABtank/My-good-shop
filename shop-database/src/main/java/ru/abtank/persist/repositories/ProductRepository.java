package ru.abtank.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abtank.persist.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

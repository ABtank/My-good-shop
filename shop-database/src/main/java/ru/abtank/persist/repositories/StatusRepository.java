package ru.abtank.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abtank.persist.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
}

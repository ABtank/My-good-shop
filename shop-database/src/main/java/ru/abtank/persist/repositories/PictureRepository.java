package ru.abtank.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abtank.persist.model.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}

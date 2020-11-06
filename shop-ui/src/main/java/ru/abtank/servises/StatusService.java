package ru.abtank.servises;

import ru.abtank.representations.StatusRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StatusService {

    List<StatusRepr> findAll();

    Optional<StatusRepr> findById(Long id);

    void deleteById(Long id);

    void save(StatusRepr statusRepr) throws IOException;
}

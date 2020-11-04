package ru.abtank.servise;

import ru.abtank.representation.BrandRepr;
import ru.abtank.representation.StatusRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StatusService {

    List<StatusRepr> findAll();

    Optional<StatusRepr> findById(Long id);

    void deleteById(Long id);

    void save(StatusRepr statusRepr) throws IOException;
}

package ru.abtank.servise;

import ru.abtank.representation.RoleRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleRepr> findAll();

    Optional<RoleRepr> findById(Long id);

    void deleteById(Long id);

    void save(RoleRepr roleRepr) throws IOException;
}

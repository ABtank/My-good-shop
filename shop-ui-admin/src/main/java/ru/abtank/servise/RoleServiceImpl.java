package ru.abtank.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Role;
import ru.abtank.persist.repositories.RoleRepository;
import ru.abtank.representation.RoleRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<RoleRepr> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(RoleRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<RoleRepr> findById(Long id) {
        return roleRepository.findById(id).map(RoleRepr::new);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(RoleRepr roleRepr) throws IOException {
        Role role = (roleRepr.getId() != null) ? roleRepository.findById(roleRepr.getId())
                .orElseThrow(NotFoundException::new) : new Role();
        role.setName(roleRepr.getName());
        roleRepository.save(role);
    }
}

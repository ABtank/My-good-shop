package ru.abtank.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.persist.model.User;
import ru.abtank.persist.repositories.RoleRepository;
import ru.abtank.persist.repositories.UserRepository;
import ru.abtank.representations.UserRepr;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserRepr userRepr) {
        User user = new User();
        user.setId(userRepr.getId());
        user.setLogin(userRepr.getUsername());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        user.setEmail(userRepr.getEmail());
        user.setRoles(roleRepository.findAll().stream().filter(p -> p.getName().equals("ROLE_USER")).collect(Collectors.toSet()));
        userRepository.save(user);
    }

    @Override
    public Optional<UserRepr> findById(Long id) {
        return userRepository.findById(id).map(UserRepr::new);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserRepr> findByLogin(String name) {
        return userRepository.findByLogin(name).map(UserRepr::new);
    }
}

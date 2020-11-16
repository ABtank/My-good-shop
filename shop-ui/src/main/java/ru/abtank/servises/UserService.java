package ru.abtank.servises;

import ru.abtank.representations.UserRepr;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(UserRepr userRepr);

    Optional<UserRepr> findById(Long id);

    void delete(Long id);
}

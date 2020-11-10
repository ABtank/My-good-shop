package ru.abtank.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Status;
import ru.abtank.persist.repositories.StatusRepository;
import ru.abtank.representations.StatusRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {

    private StatusRepository statusRepository;

    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    @Transactional
    public List<StatusRepr> findAll() {
        return statusRepository.findAll()
                .stream()
                .map(StatusRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<StatusRepr> findById(Integer id) {
        return statusRepository.findById(id).map(StatusRepr::new);
    }

}

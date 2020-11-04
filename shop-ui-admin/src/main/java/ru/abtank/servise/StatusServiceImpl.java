package ru.abtank.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Brand;
import ru.abtank.persist.model.Status;
import ru.abtank.persist.repositories.BrandRepository;
import ru.abtank.persist.repositories.StatusRepository;
import ru.abtank.representation.BrandRepr;
import ru.abtank.representation.StatusRepr;

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
    public Optional<StatusRepr> findById(Long id) {
        return statusRepository.findById(id).map(StatusRepr::new);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(StatusRepr statusRepr) throws IOException {
        Status status = (statusRepr.getId() != null) ? statusRepository.findById(statusRepr.getId())
                .orElseThrow(NotFoundException::new) : new Status();
        status.setName(statusRepr.getName());
        statusRepository.save(status);
    }
}

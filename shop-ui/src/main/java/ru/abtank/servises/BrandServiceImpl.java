package ru.abtank.servises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abtank.persist.repositories.BrandRepository;
import ru.abtank.representations.BrandRepr;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

    private BrandRepository brandRepository;

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandRepr> findAll() {
        return brandRepository.findAll().stream().map(BrandRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<BrandRepr> findById(Long id) {
        return brandRepository.findById(id).map(BrandRepr::new);
    }
}

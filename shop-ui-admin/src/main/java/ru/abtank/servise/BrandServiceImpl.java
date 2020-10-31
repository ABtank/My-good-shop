package ru.abtank.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.controllers.NotFoundException;
import ru.abtank.persist.model.Brand;
import ru.abtank.persist.repositories.BrandRepository;
import ru.abtank.representation.BrandRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public List<BrandRepr> findAll() {
        return brandRepository.findAll()
                .stream()
                .map(BrandRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<BrandRepr> findById(Long id) {
        return brandRepository.findById(id).map(BrandRepr::new);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(BrandRepr brandRepr) throws IOException {
        Brand brand = (brandRepr.getId() != null) ? brandRepository.findById(brandRepr.getId())
                .orElseThrow(NotFoundException::new) : new Brand();
        brand.setName(brandRepr.getName());
        brandRepository.save(brand);
    }
}

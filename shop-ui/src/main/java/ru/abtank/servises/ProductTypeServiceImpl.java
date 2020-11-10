package ru.abtank.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.ProductType;
import ru.abtank.persist.repositories.ProductTypeRepository;
import ru.abtank.representations.ProductTypeRepr;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeRepository productTypeRepository;

    @Autowired
    public void setProductTypeRepository(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    @Transactional
    public List<ProductTypeRepr> findAll() {
        return productTypeRepository.findAll()
                .stream()
                .map(ProductTypeRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ProductTypeRepr> findById(Integer id) {
        return productTypeRepository.findById(id).map(ProductTypeRepr::new);
    }

}

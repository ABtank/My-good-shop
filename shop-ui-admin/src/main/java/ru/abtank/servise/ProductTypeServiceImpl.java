package ru.abtank.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.ProductType;
import ru.abtank.persist.repositories.ProductTypeRepository;
import ru.abtank.representation.ProductTypeRepr;

import java.io.IOException;
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

    @Override
    @Transactional
    public void deleteById(Integer id) {
        productTypeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(ProductTypeRepr productTypeRepr) throws IOException {
        ProductType productType = (productTypeRepr.getId() != null) ? productTypeRepository.findById(productTypeRepr.getId())
                .orElseThrow(NotFoundException::new) : new ProductType();
        productType.setName(productTypeRepr.getName());
        productTypeRepository.save(productType);
    }
}

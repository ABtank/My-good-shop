package ru.abtank.servises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abtank.persist.repositories.CategoryRepository;
import ru.abtank.representations.CategoryRepr;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryRepr> findAll() {
        return categoryRepository.findAll().stream().map(CategoryRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryRepr> findById(Long id) {
        return categoryRepository.findById(id).map(CategoryRepr::new);
    }
}

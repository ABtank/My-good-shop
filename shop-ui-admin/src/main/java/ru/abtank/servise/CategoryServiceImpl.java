package ru.abtank.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Category;
import ru.abtank.persist.repositories.CategoryRepository;
import ru.abtank.representation.CategoryRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public List<CategoryRepr> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<CategoryRepr> findById(Integer id) {
        return categoryRepository.findById(id).map(CategoryRepr::new);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(CategoryRepr categoryRepr) throws IOException {
        Category category = (categoryRepr.getId() != null && categoryRepr.getId() > 0) ? categoryRepository.findById(categoryRepr.getId())
                .orElseThrow(NotFoundException::new) : new Category();
        category.setName(categoryRepr.getName());
        categoryRepository.save(category);
    }
}

package ru.abtank.servise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Picture;
import ru.abtank.persist.model.Product;
import ru.abtank.persist.repositories.ProductRepository;
import ru.abtank.representation.ProductRepr;
import ru.abtank.service.PictureService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PictureService pictureService;

    private static final Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);

    @Autowired
    public ProductsServiceImpl(ProductRepository productRepository, PictureService pictureService) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
    }

    @Override
    @Transactional
    public List<ProductRepr> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ProductRepr> findById(Long id) {
        return productRepository.findById(id).map(ProductRepr::new);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(ProductRepr productRepr) throws IOException {
        Product product = (productRepr.getId() != null) ? productRepository.findById(productRepr.getId())
                .orElseThrow(NotFoundException::new) : new Product();
        product.setName(productRepr.getName());
        product.setQuickDescription(productRepr.getQuickDescription());
        product.setDescription(productRepr.getDescription());
        product.setSize(productRepr.getSize());
        product.setDiscount(productRepr.getDiscount());
        product.setPrice(productRepr.getPrice());
        product.setCategory(productRepr.getCategory());
        product.setBrand(productRepr.getBrand());
        product.setType(productRepr.getType());
        product.setStatus(productRepr.getStatus());

        if (productRepr.getNewPictures() != null) {
            for (MultipartFile newPicture : productRepr.getNewPictures()) {
                logger.info("Product {} file {} size {}",
                        product.getId(),
                        newPicture.getOriginalFilename(),
                        newPicture.getSize());

                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes())));
            }
        }
        productRepository.save(product);
    }
}

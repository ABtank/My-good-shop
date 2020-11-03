package ru.abtank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.abtank.persist.model.Picture;
import ru.abtank.persist.model.PictureData;
import ru.abtank.persist.repositories.PictureRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;


public class PictureServiceFileImpl implements PictureService {

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceFileImpl.class);

    @Value("${picture.storage.path}")
    private String storagePath;

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceFileImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return pictureRepository.findById(id)
                .map(Picture::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {

        return pictureRepository.findById(id)
                .filter(pic -> pic.getPictureData().getFileName() != null)
                .map(pic -> Paths.get(storagePath, pic.getPictureData().getFileName()))
                .filter(Files::exists)
                .map(this::getBytes);
    }

    private byte[] getBytes(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException ex) {
            logger.error("Can't read picture file ", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        String fileName = UUID.randomUUID().toString();
        try (OutputStream outputStream = Files.newOutputStream(Path.of(storagePath, fileName))) {
            outputStream.write(picture);
        } catch (IOException ex) {
            logger.error("Can't create picture file ", ex);
            throw new RuntimeException(ex);
        }
        return new PictureData(fileName);
    }
}

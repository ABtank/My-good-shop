package ru.abtank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abtank.persist.model.Picture;
import ru.abtank.persist.model.PictureData;
import ru.abtank.persist.repositories.PictureRepository;

import java.util.Optional;


public class PictureServiceBlobImpl implements PictureService{

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceBlobImpl.class);

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceBlobImpl(PictureRepository pictureRepository) {
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
                .filter(picture -> picture.getPictureData()!=null)
                .map(picture -> picture.getPictureData().getData());
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        return new PictureData(picture);
    }
}

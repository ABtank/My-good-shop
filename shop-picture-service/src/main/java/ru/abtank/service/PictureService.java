package ru.abtank.service;

import ru.abtank.persist.model.PictureData;
import ru.abtank.persist.repositories.PictureRepository;

import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);
}

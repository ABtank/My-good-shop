package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Picture;
import ru.abtank.persist.repositories.PictureRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/picture")
public class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

//    Так как нам надо отправить бинарный поток байтов,то мы пользуемся HttpServletResponse
    @GetMapping("/{pictureId}")
    public void downloadProductPicture(@PathVariable("pictureId") Long pictureId, HttpServletResponse response) throws IOException {
        logger.info("Downloading picture {}", pictureId);

        Optional<Picture> picture = pictureRepository.findById(pictureId);
        if (picture.isPresent()) {  // если картинка есть
            logger.info("picture exist");
            response.setContentType(picture.get().getContentType()); //расширение
            response.getOutputStream().write(picture.get().getPictureData().getData()); // передаем массив байт
            return;
        }
        throw new NotFoundException(pictureId.toString(),Picture.class.getSimpleName());
    }
}

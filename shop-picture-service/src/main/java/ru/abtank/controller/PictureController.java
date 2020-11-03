package ru.abtank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.persist.model.Picture;
import ru.abtank.service.PictureService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/picture")
public class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    //    Так как нам надо отправить бинарный поток байтов,то мы пользуемся HttpServletResponse
    @GetMapping("/{pictureId}")
    public void downloadProductPicture(@PathVariable("pictureId") Long pictureId, HttpServletResponse response) throws IOException {
        logger.info("Downloading picture {}", pictureId);

        Optional<String> optional = pictureService.getPictureContentTypeById(pictureId);
        if (optional.isPresent()) {  // если картинка есть
            logger.info("picture exist");
            response.setContentType(optional.get());
            response.getOutputStream().write(pictureService.getPictureDataById(pictureId).get()); // передаем массив байт
        }else {
            throw new NotFoundException(pictureId.toString(), Picture.class.getSimpleName());
        }
    }
}

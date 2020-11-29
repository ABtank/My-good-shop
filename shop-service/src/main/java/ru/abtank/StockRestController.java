package ru.abtank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping ("/storage")
public class StockRestController {

    private static final Logger logger = LoggerFactory.getLogger(StockRestController.class);

    @GetMapping("/{id}")
    public Stock getStockById (@PathVariable("id") Long id){
        logger.info("getStockById in storage id = {} ", id);
        return new Stock(id, new Random().nextInt(100));
    }
}

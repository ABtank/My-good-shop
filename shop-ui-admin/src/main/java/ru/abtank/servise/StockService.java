package ru.abtank.servise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "stock-client", url = "http://localhost:8082/storage")
public interface StockService {

    @GetMapping("/{id}")
    //должен быть один в один как и на shop-service
    Stock getStockById (@PathVariable("id") Long id);

}

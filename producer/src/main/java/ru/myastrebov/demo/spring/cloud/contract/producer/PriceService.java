package ru.myastrebov.demo.spring.cloud.contract.producer;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author m.yastrebov
 */
@Service
public class PriceService {

    private static final Map<String, CarPrice> PRICES;

    static {
        PRICES = new ConcurrentHashMap<>();
        PRICES.put("BMW", new CarPrice("BMW", 100, "RUB"));
        PRICES.put("Mercedes", new CarPrice("Mercedes", 200, "RUB"));
        PRICES.put("Audi", new CarPrice("Audi", 300, "RUB"));
    }


    Map<String, CarPrice> getAll() {
        return PRICES;
    }

    CarPrice getPrice(String car) {
        return PRICES.get(car);
    }

    CarPrice update(String car, CarPrice price) {
        price.setUuid(UUID.randomUUID().toString());
        return PRICES.put(car, price);
    }

    CarPrice remove(String car) {
        return PRICES.remove(car);
    }
}

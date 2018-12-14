package ru.myastrebov.demo.spring.cloud.contract.producer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author m.yastrebov
 */
@RestController
@RequestMapping("/pricing")
@AllArgsConstructor
public class PricingController {

    private final PriceService priceService;

    @GetMapping("/calculate")
    public CarPrice calculatePrice(@RequestParam("car") String car) {
        return priceService.getPrice(car);
    }

    @GetMapping("/all")
    public List<CarPrice> getAll() {
        return new ArrayList<>(priceService.getAll().values());
    }

    @PutMapping("/update")
    public CarPrice updatePrice(@RequestBody CarPrice newPrice) {
        return priceService.update(newPrice.getCar(), newPrice);
    }

    @DeleteMapping("/delete")
    public boolean removePrice(@RequestParam("car") String car) {
        return priceService.remove(car) != null;
    }
}

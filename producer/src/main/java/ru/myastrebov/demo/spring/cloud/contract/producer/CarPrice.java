package ru.myastrebov.demo.spring.cloud.contract.producer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author m.yastrebov
 */
@Data
@NoArgsConstructor
class CarPrice {

    private String uuid;

    private String car;

    private Integer amount;

    private String currency;

    CarPrice(String car, Integer amount, String currency) {
        this.car = car;
        this.amount = amount;
        this.currency = currency;
        this.uuid = UUID.randomUUID().toString();
    }
}

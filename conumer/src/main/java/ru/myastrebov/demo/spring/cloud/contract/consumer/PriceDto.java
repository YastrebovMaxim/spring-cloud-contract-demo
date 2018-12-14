package ru.myastrebov.demo.spring.cloud.contract.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author m.yastrebov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {

    private String uuid;

    private String car;

    private Integer amount;

    private String currency;
}

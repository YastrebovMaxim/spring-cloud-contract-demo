package ru.myastrebov.demo.spring.cloud.contract.producer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;
import wiremock.com.google.common.collect.ImmutableMap;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author m.yastrebov
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProducerApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public abstract class BaseContractTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        when(priceService.getAll()).thenReturn(
                ImmutableMap.of(
                        "Lada", new CarPrice("Lada", 1, "RUB"),
                        "Nissan", new CarPrice("Nissan", 2, "USD")
                )
        );
        when(priceService.getPrice(eq("BMW")))
                .thenReturn(new CarPrice("BMW", 123, "EUR"));
        when(priceService.remove(eq("Mercedes")))
                .thenReturn(null);
        when(priceService.update(eq("BMW"), any(CarPrice.class)))
                .thenAnswer(invocation -> {
                    CarPrice argument = invocation.getArgument(1);
                    argument.setUuid(UUID.randomUUID().toString());
                    return argument;
                });
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }
}

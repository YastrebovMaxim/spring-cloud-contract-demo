package ru.myastrebov.demo.spring.cloud.contract.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureStubRunner(
        ids = "ru.myastrebov:producer:+:stubs",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@SpringBootTest(classes = ConsumerApplication.class)
class PricingClientTest {

    @Autowired
    private PricingClient uut;

    @Test
    void shouldGetAllPrices() throws Exception {
        List<PriceDto> list = uut.getAll();
        assertThat(list).hasSize(2);
    }

    @Test
    void shouldCalculatePrice() throws Exception {
        PriceDto priceDto = uut.calculatePrice("BMW");
        assertThat(priceDto).isEqualTo(new PriceDto("064731d9-cbd8-4ff7-85be-075af89c97ef", "BMW", 123, "EUR"));
    }

    @Test
    void shouldUpdatePrice() throws Exception {
        PriceDto priceDto = uut.updatePrice(new PriceDto(null, "BMW", 500, "EUR"));
        assertThat(priceDto.getUuid()).isNotBlank();
        assertThat(priceDto.getCar()).isEqualTo("BMW");
        assertThat(priceDto.getAmount()).isEqualTo(500);
        assertThat(priceDto.getCurrency()).isEqualTo("EUR");
    }
}
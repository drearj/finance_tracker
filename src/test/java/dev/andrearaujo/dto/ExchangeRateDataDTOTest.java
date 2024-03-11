package dev.andrearaujo.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ExchangeRateDataDTOTest {

    @Test
    void testGettersAndSetters() {
        ExchangeRateDataDTO exchangeRateDataDTO = new ExchangeRateDataDTO();

        exchangeRateDataDTO.setCountry("Brazil");
        exchangeRateDataDTO.setCurrency("Real");
        exchangeRateDataDTO.setExchange_rate(BigDecimal.valueOf(4.852));
        exchangeRateDataDTO.setEffective_date("2023-12-31");

        Assertions.assertEquals("Brazil", exchangeRateDataDTO.getCountry());
        Assertions.assertEquals("Real", exchangeRateDataDTO.getCurrency());
        Assertions.assertEquals(BigDecimal.valueOf(4.852), exchangeRateDataDTO.getExchange_rate());
        Assertions.assertEquals("2023-12-31", exchangeRateDataDTO.getEffective_date());
    }
}
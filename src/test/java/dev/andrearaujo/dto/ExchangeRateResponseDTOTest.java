package dev.andrearaujo.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class ExchangeRateResponseDTOTest {

    @Test
    void testGettersAndSetters() {
        ExchangeRateResponseDTO exchangeRateResponseDTO = new ExchangeRateResponseDTO();

        List<ExchangeRateDataDTO> data = new ArrayList<>();
        ExchangeRateDataDTO exchangeRateDataDTO = new ExchangeRateDataDTO();
        exchangeRateDataDTO.setCountry("Brazil");
        exchangeRateDataDTO.setCurrency("Real");
        exchangeRateDataDTO.setExchange_rate(BigDecimal.valueOf(4.852));
        exchangeRateDataDTO.setEffective_date("2023-12-31");
        data.add(exchangeRateDataDTO);

        ExchangeRateMetaDTO meta = new ExchangeRateMetaDTO();

        ExchangeRateLinksDTO links = new ExchangeRateLinksDTO();

        exchangeRateResponseDTO.setData(data);
        exchangeRateResponseDTO.setMeta(meta);
        exchangeRateResponseDTO.setLinks(links);

        Assertions.assertEquals(data, exchangeRateResponseDTO.getData());
        Assertions.assertEquals(meta, exchangeRateResponseDTO.getMeta());
        Assertions.assertEquals(links, exchangeRateResponseDTO.getLinks());
    }
}
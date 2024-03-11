package dev.andrearaujo.service;

import dev.andrearaujo.dto.ExchangeRateDataDTO;
import dev.andrearaujo.dto.ExchangeRateResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class TreasuryApiServiceImplTest {

    @Value("${treasury.api.url}")
    private String apiUrl;

    @InjectMocks
    private TreasuryApiServiceImpl treasuryApiService;

    @Mock
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void getExchangeRates_Success() throws ParseException {
        String currency = "USD";
        Date transactionDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");

        LocalDate sixMonthsAgo = LocalDate.ofInstant(transactionDate.toInstant(), ZoneId.systemDefault()).minusMonths(6);
        Date sixMonthsAgoDate = Date.from(sixMonthsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String filter = String.format("currency:eq:%s,effective_date:gte:%s,effective_date:lte:%s",
                currency,
                new SimpleDateFormat("yyyy-MM-dd").format(sixMonthsAgoDate),
                new SimpleDateFormat("yyyy-MM-dd").format(transactionDate)
        );

        String fields = "country,currency,exchange_rate,effective_date";

        String expectedUrl = UriComponentsBuilder
                .fromUriString(apiUrl + "/v1/accounting/od/rates_of_exchange")
                .queryParam("filter", filter)
                .queryParam("fields", fields)
                .build()
                .toUriString();



        ExchangeRateResponseDTO responseDTO = new ExchangeRateResponseDTO();
        ExchangeRateDataDTO exchangeRateData = new ExchangeRateDataDTO();
        exchangeRateData.setCountry("US");
        exchangeRateData.setCurrency("USD");
        exchangeRateData.setExchange_rate(new BigDecimal("1.0"));
        exchangeRateData.setEffective_date("2022-01-01");
        responseDTO.setData(List.of(exchangeRateData));

        when(restTemplate.getForEntity(anyString(), eq(ExchangeRateResponseDTO.class)))
                .thenReturn(new ResponseEntity<>(responseDTO, HttpStatus.OK));

        mockServer.expect(requestTo(expectedUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"data\":[{\"country\":\"US\",\"currency\":\"USD\",\"exchange_rate\":\"1.0\",\"effective_date\":\"2022-01-01\"}],\"meta\":{},\"links\":{}}", MediaType.APPLICATION_JSON));

        ResponseEntity<ExchangeRateResponseDTO> result = treasuryApiService.getExchangeRates(currency, transactionDate);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseDTO, result.getBody());
    }
}
package dev.andrearaujo.service;

import dev.andrearaujo.dto.ExchangeRateDataDTO;
import dev.andrearaujo.dto.ExchangeRateResponseDTO;
import dev.andrearaujo.dto.PurchaseTransactionConversionDTO;
import dev.andrearaujo.error.ResourceNotFoundException;
import dev.andrearaujo.model.PurchaseTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyConversionServiceTest {

    @Mock
    private PurchaseTransactionService purchaseTransactionService;

    @Mock
    private TreasuryApiService treasuryApiService;

    @InjectMocks
    private CurrencyConversionService currencyConversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertPurchaseToCurrency() throws ParseException {
        Long purchaseId = 1L;
        String targetCurrency = "EUR";
        Date transactionDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");

        PurchaseTransaction purchaseTransaction = new PurchaseTransaction();
        purchaseTransaction.setUuid(java.util.UUID.randomUUID());
        purchaseTransaction.setDescription("Test Purchase");
        purchaseTransaction.setTransactionDate(transactionDate);
        purchaseTransaction.setPurchaseAmount(new BigDecimal("100.00"));

        ExchangeRateDataDTO exchangeRateDataDTO = new ExchangeRateDataDTO();
        exchangeRateDataDTO.setExchange_rate(new BigDecimal("0.85"));
        exchangeRateDataDTO.setCurrency("EUR");
        exchangeRateDataDTO.setEffective_date("2022-01-01");

        List<ExchangeRateDataDTO> exchangeRateList = new ArrayList<>();
        exchangeRateList.add(exchangeRateDataDTO);

        ExchangeRateResponseDTO exchangeRateResponseDTO = new ExchangeRateResponseDTO();
        exchangeRateResponseDTO.setData(exchangeRateList);

        ResponseEntity<ExchangeRateResponseDTO> exchangeRatesResponse = ResponseEntity.ok(exchangeRateResponseDTO);

        when(purchaseTransactionService.getTransactionById(purchaseId)).thenReturn(purchaseTransaction);
        when(treasuryApiService.getExchangeRates(targetCurrency, transactionDate)).thenReturn(exchangeRatesResponse);

        PurchaseTransactionConversionDTO conversionDTO = currencyConversionService.convertPurchaseToCurrency(purchaseId, targetCurrency);

        assertNotNull(conversionDTO);
        assertEquals(purchaseTransaction.getUuid(), conversionDTO.getTransactionUUID());
        assertEquals(purchaseTransaction.getDescription(), conversionDTO.getDescription());
        assertEquals(purchaseTransaction.getTransactionDate(), conversionDTO.getTransactionDate());
        assertEquals(purchaseTransaction.getPurchaseAmount(), conversionDTO.getOriginalPurchaseAmount());
        assertEquals(exchangeRateDataDTO.getExchange_rate(), conversionDTO.getExchangeRate());
        assertEquals(new BigDecimal("85.00"), conversionDTO.getConvertedAmount());
        assertEquals(exchangeRateDataDTO.getCurrency(), conversionDTO.getCurrency());

        verify(purchaseTransactionService, times(1)).getTransactionById(purchaseId);
        verify(treasuryApiService, times(1)).getExchangeRates(targetCurrency, transactionDate);
    }

    @Test
    void testConvertPurchaseToCurrencyWithInvalidPurchaseId() {
        Long invalidPurchaseId = 2L;
        String targetCurrency = "EUR";

        when(purchaseTransactionService.getTransactionById(invalidPurchaseId)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> currencyConversionService.convertPurchaseToCurrency(invalidPurchaseId, targetCurrency));

        verify(purchaseTransactionService, times(1)).getTransactionById(invalidPurchaseId);
    }

}
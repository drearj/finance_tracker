package dev.andrearaujo.controller;

import dev.andrearaujo.dto.PurchaseTransactionConversionDTO;
import dev.andrearaujo.error.ResourceNotFoundException;
import dev.andrearaujo.service.CurrencyConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class CurrencyConversionControllerTest {

    @Mock
    private CurrencyConversionService currencyConversionService;

    @InjectMocks
    private CurrencyConversionController currencyConversionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void convertPurchaseToCurrency_Success() throws ParseException {
        PurchaseTransactionConversionDTO mockedResult = new PurchaseTransactionConversionDTO(
                UUID.randomUUID(),
                "Sample Description",
                new Date(),
                new BigDecimal("100.00"),
                new BigDecimal("1.20"),
                new BigDecimal("120.00"),
                "EUR",
                "European Pound"
        );
        when(currencyConversionService.convertPurchaseToCurrency(anyLong(), anyString())).thenReturn(mockedResult);

        ResponseEntity<?> responseEntity = currencyConversionController.convertPurchaseToCurrency(1L, "EUR");

        verify(currencyConversionService, times(1)).convertPurchaseToCurrency(1L, "EUR");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        PurchaseTransactionConversionDTO responseBody = (PurchaseTransactionConversionDTO) responseEntity.getBody();

        assert responseBody != null;
        assertEquals(mockedResult.getTransactionUUID(), responseBody.getTransactionUUID());
        assertEquals("Sample Description", responseBody.getDescription());
        assertEquals(mockedResult.getTransactionDate(), responseBody.getTransactionDate());
        assertEquals(new BigDecimal("100.00"), responseBody.getOriginalPurchaseAmount());
        assertEquals(new BigDecimal("1.20"), responseBody.getExchangeRate());
        assertEquals(new BigDecimal("120.00"), responseBody.getConvertedAmount());
        assertEquals("EUR", responseBody.getCurrency());
    }

    @Test
    void convertPurchaseToCurrency_NotFound() throws ParseException {
        when(currencyConversionService.convertPurchaseToCurrency(anyLong(), anyString())).thenThrow(new ResourceNotFoundException("Transaction not found"));

        ResponseEntity<?> responseEntity = currencyConversionController.convertPurchaseToCurrency(1L, "EUR");

        verify(currencyConversionService, times(1)).convertPurchaseToCurrency(1L, "EUR");
        assertSame(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
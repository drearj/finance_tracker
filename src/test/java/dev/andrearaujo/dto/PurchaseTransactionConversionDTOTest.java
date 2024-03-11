package dev.andrearaujo.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

class PurchaseTransactionConversionDTOTest {

    @Test
    void testGettersAndSetters() {
        UUID transactionUUID = UUID.randomUUID();
        String description = "Sample Description";
        Date transactionDate = new Date();
        BigDecimal originalPurchaseAmount = BigDecimal.valueOf(100.50);
        BigDecimal exchangeRate = BigDecimal.valueOf(5.0);
        BigDecimal convertedAmount = BigDecimal.valueOf(502.50);
        String currency = "USD";

        PurchaseTransactionConversionDTO purchaseTransactionConversionDTO =
                new PurchaseTransactionConversionDTO(transactionUUID, description, transactionDate,
                        originalPurchaseAmount, exchangeRate, convertedAmount, currency);

        Assertions.assertEquals(transactionUUID, purchaseTransactionConversionDTO.getTransactionUUID());
        Assertions.assertEquals(description, purchaseTransactionConversionDTO.getDescription());
        Assertions.assertEquals(transactionDate, purchaseTransactionConversionDTO.getTransactionDate());
        Assertions.assertEquals(originalPurchaseAmount, purchaseTransactionConversionDTO.getOriginalPurchaseAmount());
        Assertions.assertEquals(exchangeRate, purchaseTransactionConversionDTO.getExchangeRate());
        Assertions.assertEquals(convertedAmount, purchaseTransactionConversionDTO.getConvertedAmount());
        Assertions.assertEquals(currency, purchaseTransactionConversionDTO.getCurrency());
    }
}
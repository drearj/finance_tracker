package dev.andrearaujo.service;

import dev.andrearaujo.dto.ExchangeRateDataDTO;
import dev.andrearaujo.dto.ExchangeRateResponseDTO;
import dev.andrearaujo.dto.PurchaseTransactionConversionDTO;
import dev.andrearaujo.error.ResourceNotFoundException;
import dev.andrearaujo.model.PurchaseTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CurrencyConversionService {

    @Autowired private PurchaseTransactionService purchaseTransactionService;
    @Autowired private TreasuryApiService treasuryApiService;

    public PurchaseTransactionConversionDTO convertPurchaseToCurrency(Long purchaseId, String targetCurrency) throws ParseException {

        PurchaseTransaction transaction = purchaseTransactionService.getTransactionById(purchaseId);
        if(transaction == null) throw new ResourceNotFoundException("Transaction not found with id: " + purchaseId);

        ResponseEntity<ExchangeRateResponseDTO> exchangeRatesResponse = treasuryApiService.getExchangeRates(targetCurrency, transaction.getTransactionDate());
        ExchangeRateResponseDTO exchangeRates = exchangeRatesResponse.getBody();

        List<ExchangeRateDataDTO> extractedRates = exchangeRates.getData();

        ExchangeRateDataDTO closestExchangeRate = this.findClosestExchangeRate(transaction.getTransactionDate(), extractedRates);
        if(closestExchangeRate == null) throw new ResourceNotFoundException("ExchangeRates was not found");

        return createConversionDTO(transaction, closestExchangeRate);
    }

    private ExchangeRateDataDTO findClosestExchangeRate(Date transactionDate, List<ExchangeRateDataDTO> exchangeRates) throws ParseException {
        ExchangeRateDataDTO closestExchangeRate = null;
        long closestDifference = Long.MAX_VALUE;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (ExchangeRateDataDTO exchangeRate : exchangeRates) {
            Date exchangeRateDate = dateFormat.parse(exchangeRate.getEffective_date());
            long difference = Math.abs(transactionDate.getTime() - exchangeRateDate.getTime());

            if (difference < closestDifference) {
                closestDifference = difference;
                closestExchangeRate = exchangeRate;
            }
        }

        return closestExchangeRate;
    }

    private PurchaseTransactionConversionDTO createConversionDTO(PurchaseTransaction transaction, ExchangeRateDataDTO exchangeRate) {

        BigDecimal purchaseAmount = transaction.getPurchaseAmount();
        BigDecimal convertedAmount = purchaseAmount.multiply(exchangeRate.getExchange_rate());

        PurchaseTransactionConversionDTO conversion = new PurchaseTransactionConversionDTO(
            transaction.getUuid(),
            transaction.getDescription(),
            transaction.getTransactionDate(),
            transaction.getPurchaseAmount(),
            exchangeRate.getExchange_rate(),
            convertedAmount.setScale(2, RoundingMode.HALF_UP),
            exchangeRate.getCurrency()
        );

        return conversion;
    }
}
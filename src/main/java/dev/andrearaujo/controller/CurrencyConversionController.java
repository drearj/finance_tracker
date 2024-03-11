package dev.andrearaujo.controller;

import dev.andrearaujo.dto.PurchaseTransactionConversionDTO;
import dev.andrearaujo.error.ResourceNotFoundException;
import dev.andrearaujo.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @GetMapping("/purchase/{id}")
    public ResponseEntity<?> convertPurchaseToCurrency(@PathVariable Long id,
                                                       @RequestParam String targetCurrency) throws ParseException {
        try {
            PurchaseTransactionConversionDTO convertedTransaction = this.currencyConversionService.convertPurchaseToCurrency(id, targetCurrency);
            return ResponseEntity.ok(convertedTransaction);
        } catch(ResourceNotFoundException notFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
        }
    }
}

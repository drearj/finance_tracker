package dev.andrearaujo.service;

import dev.andrearaujo.dto.ExchangeRateResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface TreasuryApiService {
    ResponseEntity<ExchangeRateResponseDTO> getExchangeRates(String currency, Date transactionDate);
}

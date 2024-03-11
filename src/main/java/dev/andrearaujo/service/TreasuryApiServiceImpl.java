package dev.andrearaujo.service;

import dev.andrearaujo.dto.ExchangeRateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TreasuryApiServiceImpl implements TreasuryApiService {

    @Value("${treasury.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    public TreasuryApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<ExchangeRateResponseDTO> getExchangeRates(String currency, Date transactionDate) {
        LocalDate sixMonthsAgo = LocalDate.ofInstant(transactionDate.toInstant(), ZoneId.systemDefault())
                .minusMonths(6);
        Date sixMonthsAgoDate = Date.from(sixMonthsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String filter = String.format("country_currency_desc:eq:%s,effective_date:gte:%s,effective_date:lte:%s",
                currency,
                dateFormatter.format(sixMonthsAgoDate),
                dateFormatter.format(transactionDate)
        );

        String fields = "country,currency, country_currency_desc,exchange_rate,effective_date";

        System.out.println(String.format("[EXTERNAL API] - Requesting Treasure API :: " +
                "GET rates_of_exchanges :: " +
                "with currency %s and transaction date %s", currency, transactionDate));
        String apiUrl = UriComponentsBuilder
                .fromUriString(this.apiUrl + "/v1/accounting/od/rates_of_exchange")
                .queryParam("filter", filter)
                .queryParam("fields", fields)
                .build()
                .toUriString();

        return restTemplate.getForEntity(apiUrl, ExchangeRateResponseDTO.class);
    }
}

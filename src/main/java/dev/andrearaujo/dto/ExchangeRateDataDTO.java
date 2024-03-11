package dev.andrearaujo.dto;

import java.math.BigDecimal;

public class ExchangeRateDataDTO {
    private String country;
    private String currency;
    private String country_currency_desc;
    private BigDecimal exchange_rate;
    private String effective_date;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry_currency_desc() {
        return country_currency_desc;
    }
    public void setCountry_currency_desc(String country_currency_desc) {
        this.country_currency_desc = country_currency_desc;
    }

    public BigDecimal getExchange_rate() {
        return exchange_rate;
    }
    public void setExchange_rate(BigDecimal exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getEffective_date() {
        return effective_date;
    }
    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }
}

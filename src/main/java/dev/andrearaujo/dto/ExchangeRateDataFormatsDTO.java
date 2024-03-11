package dev.andrearaujo.dto;

public class ExchangeRateDataFormatsDTO {
    private String country;
    private String currency;
    private String exchange_rate;
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

    public String getExchange_rate() {
        return exchange_rate;
    }
    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getEffective_date() {
        return effective_date;
    }
    public void setEffective_date(String effective_date) {
        this.effective_date = effective_date;
    }
}

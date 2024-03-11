package dev.andrearaujo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class PurchaseTransactionConversionDTO {
    private String description;
    private Date transactionDate;
    private BigDecimal originalPurchaseAmount;
    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;
    private String currency;
    private String countryCurrencyDesc;
    private UUID transactionUUID;

    public PurchaseTransactionConversionDTO(UUID transactionUUID,
                                            String description,
                                            Date transactionDate,
                                            BigDecimal originalPurchaseAmount,
                                            BigDecimal exchangeRate,
                                            BigDecimal convertedAmount,
                                            String currency,
                                            String countryCurrencyDesc) {
        this.transactionUUID = transactionUUID;
        this.description = description;
        this.transactionDate = transactionDate;
        this.originalPurchaseAmount = originalPurchaseAmount;
        this.exchangeRate = exchangeRate;
        this.convertedAmount = convertedAmount;
        this.currency = currency;
        this.countryCurrencyDesc = countryCurrencyDesc;
    }

    public UUID getTransactionUUID() {
        return transactionUUID;
    }
    public void setTransactionUUID(UUID transactionUUID) {
        this.transactionUUID = transactionUUID;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getOriginalPurchaseAmount() {
        return originalPurchaseAmount;
    }
    public void setOriginalPurchaseAmount(BigDecimal originalPurchaseAmount) {
        this.originalPurchaseAmount = originalPurchaseAmount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }
    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }
    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountryCurrencyDesc() {
        return countryCurrencyDesc;
    }
    public void setCountryCurrencyDesc(String countryCurrencyDesc) {
        this.countryCurrencyDesc = countryCurrencyDesc;
    }
}

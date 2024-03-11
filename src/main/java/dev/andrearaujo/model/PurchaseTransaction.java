package dev.andrearaujo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.UUID;


@Entity
public class PurchaseTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50, message = "Description must not exceed 50 characters")
    private String description;

    @NotNull(message = "Transaction date is required")
    private Date transactionDate;

    private String currency = "USD";

    @Positive(message = "Purchase amount must be a positive value")
    private BigDecimal purchaseAmount;

    private UUID uuid;

    private boolean deleted;

    public PurchaseTransaction() {
        this.uuid = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }
    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = this.roundToNearestCent(purchaseAmount);
    }



    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private BigDecimal roundToNearestCent(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
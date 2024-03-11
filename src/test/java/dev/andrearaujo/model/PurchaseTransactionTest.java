package dev.andrearaujo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

class PurchaseTransactionTest {

    @Test
    public void testPurchaseTransaction() {
        Long id = 1L;
        UUID uuid = UUID.randomUUID();
        String description = "Example Description";
        Date transactionDate = new Date();
        BigDecimal purchaseAmount = new BigDecimal("100.50");
        String currency = "USD";
        boolean deleted = false;

        PurchaseTransaction purchaseTransaction = new PurchaseTransaction();
        purchaseTransaction.setId(id);
        purchaseTransaction.setUuid(uuid);
        purchaseTransaction.setDescription(description);
        purchaseTransaction.setTransactionDate(transactionDate);
        purchaseTransaction.setPurchaseAmount(purchaseAmount);
        purchaseTransaction.setCurrency(currency);
        purchaseTransaction.setDeleted(deleted);

        Assertions.assertEquals(id, purchaseTransaction.getId());
        Assertions.assertEquals(uuid, purchaseTransaction.getUuid());
        Assertions.assertEquals(description, purchaseTransaction.getDescription());
        Assertions.assertEquals(transactionDate, purchaseTransaction.getTransactionDate());
        Assertions.assertEquals(purchaseAmount, purchaseTransaction.getPurchaseAmount());
        Assertions.assertEquals(currency, purchaseTransaction.getCurrency());
        Assertions.assertEquals(deleted, purchaseTransaction.isDeleted());
    }

    @Test
    void roundToNearestCent_shouldRoundValueCorrectly() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        PurchaseTransaction transaction = new PurchaseTransaction();

        BigDecimal input = new BigDecimal("10.555");
        BigDecimal expected = new BigDecimal("10.56");

        Method roundToNearestCentMethod = PurchaseTransaction.class.getDeclaredMethod("roundToNearestCent", BigDecimal.class);
        roundToNearestCentMethod.setAccessible(true);

        BigDecimal result = (BigDecimal) roundToNearestCentMethod.invoke(transaction, input);

        assertEquals(expected, result);
    }

    @Test
    void setPurchaseAmount_shouldRoundToNearestCent() {
        PurchaseTransaction transaction = new PurchaseTransaction();

        BigDecimal input = new BigDecimal("15.555");
        BigDecimal expected = new BigDecimal("15.56");

        transaction.setPurchaseAmount(input);

        assertEquals(expected, transaction.getPurchaseAmount());
    }

}
package dev.andrearaujo.service;

import dev.andrearaujo.model.PurchaseTransaction;
import dev.andrearaujo.repository.PurchaseTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseTransactionServiceTest {

    @InjectMocks
    private PurchaseTransactionService purchaseTransactionService;

    @Mock
    private PurchaseTransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveTransaction_Success() {
        PurchaseTransaction transactionToSave = new PurchaseTransaction();
        transactionToSave.setDescription("Test Purchase");
        transactionToSave.setTransactionDate(null);
        transactionToSave.setPurchaseAmount(new BigDecimal("100.00"));

        PurchaseTransaction savedTransaction = new PurchaseTransaction();
        savedTransaction.setId(1L);
        savedTransaction.setUuid(UUID.randomUUID());
        savedTransaction.setDescription("Test Purchase");
        savedTransaction.setTransactionDate(null);
        savedTransaction.setPurchaseAmount(new BigDecimal("100.00"));

        when(transactionRepository.save(transactionToSave)).thenReturn(savedTransaction);

        PurchaseTransaction result = purchaseTransactionService.saveTransaction(transactionToSave);

        assertNotNull(result);
        assertEquals(savedTransaction.getId(), result.getId());
        assertNotNull(result.getUuid());
        assertEquals("Test Purchase", result.getDescription());
        assertNull(result.getTransactionDate());
        assertEquals(new BigDecimal("100.00"), result.getPurchaseAmount());

        verify(transactionRepository, times(1)).save(transactionToSave);
    }

    @Test
    void getAllTransactions_Success() {
        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setId(1L);
        transaction.setUuid(UUID.randomUUID());
        transaction.setDescription("Test Purchase");
        transaction.setTransactionDate(null);
        transaction.setPurchaseAmount(new BigDecimal("100.00"));

        List<PurchaseTransaction> transactionsList = Collections.singletonList(transaction);

        when(transactionRepository.findByDeletedFalse()).thenReturn(transactionsList);

        List<PurchaseTransaction> result = purchaseTransactionService.getAllTransactions();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        verify(transactionRepository, times(1)).findByDeletedFalse();
    }

    @Test
    void getTransactionById_Success() {
        Long transactionId = 1L;
        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setId(transactionId);
        transaction.setUuid(UUID.randomUUID());
        transaction.setDescription("Test Purchase");
        transaction.setTransactionDate(null);
        transaction.setPurchaseAmount(new BigDecimal("100.00"));

        when(transactionRepository.findByIdAndDeletedFalse(transactionId)).thenReturn(Optional.of(transaction));

        PurchaseTransaction result = purchaseTransactionService.getTransactionById(transactionId);

        assertNotNull(result);
        assertEquals(transactionId, result.getId());
        assertNotNull(result.getUuid());
        assertEquals("Test Purchase", result.getDescription());
        assertNull(result.getTransactionDate());
        assertEquals(new BigDecimal("100.00"), result.getPurchaseAmount());

        verify(transactionRepository, times(1)).findByIdAndDeletedFalse(transactionId);
    }

    @Test
    void getTransactionById_NotFound() {
        Long transactionId = 1L;
        when(transactionRepository.findByIdAndDeletedFalse(transactionId)).thenReturn(Optional.empty());

        assertNull(purchaseTransactionService.getTransactionById(transactionId));

        verify(transactionRepository, times(1)).findByIdAndDeletedFalse(transactionId);
    }

    @Test
    void updateTransaction_Success() {
        Long transactionId = 1L;
        PurchaseTransaction existingTransaction = new PurchaseTransaction();
        existingTransaction.setId(transactionId);
        existingTransaction.setUuid(UUID.randomUUID());
        existingTransaction.setDescription("Old Description");
        existingTransaction.setTransactionDate(null);
        existingTransaction.setPurchaseAmount(new BigDecimal("50.00"));

        PurchaseTransaction updatedTransaction = new PurchaseTransaction();
        updatedTransaction.setDescription("New Description");
        updatedTransaction.setTransactionDate(null);
        updatedTransaction.setPurchaseAmount(new BigDecimal("100.00"));

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(existingTransaction));
        when(transactionRepository.save(existingTransaction)).thenReturn(existingTransaction);

        PurchaseTransaction result = purchaseTransactionService.updateTransaction(transactionId, updatedTransaction);

        assertNotNull(result);
        assertEquals(transactionId, result.getId());
        assertNotNull(result.getUuid());
        assertEquals("New Description", result.getDescription());
        assertNull(result.getTransactionDate());
        assertEquals(new BigDecimal("100.00"), result.getPurchaseAmount());

        verify(transactionRepository, times(1)).findById(transactionId);
        verify(transactionRepository, times(1)).save(existingTransaction);
    }
}
package dev.andrearaujo.repository;

import dev.andrearaujo.model.PurchaseTransaction;
import dev.andrearaujo.service.PurchaseTransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PurchaseTransactionRepositoryTest {

    @Mock
    private PurchaseTransactionRepository repository;

    @InjectMocks
    private PurchaseTransactionService transactionService;

    @Test
    public void testFindByIdAndDeletedFalse() {
        Long id = 1L;
        PurchaseTransaction mockTransaction = new PurchaseTransaction();
        mockTransaction.setId(id);
        mockTransaction.setDeleted(false);

        when(repository.findByIdAndDeletedFalse(id)).thenReturn(Optional.of(mockTransaction));

        PurchaseTransaction result = transactionService.getTransactionById(id);

        assertEquals(mockTransaction, result);
    }

    @Test
    public void testFindByDeletedFalse() {
        PurchaseTransaction mockTransaction1 = new PurchaseTransaction();
        PurchaseTransaction mockTransaction2 = new PurchaseTransaction();
        List<PurchaseTransaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(mockTransaction1);
        mockTransactions.add(mockTransaction2);

        when(repository.findByDeletedFalse()).thenReturn(mockTransactions);

        List<PurchaseTransaction> result = transactionService.getAllTransactions();

        assertEquals(mockTransactions, result);
    }

}
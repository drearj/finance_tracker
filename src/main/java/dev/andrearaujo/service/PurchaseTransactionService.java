package dev.andrearaujo.service;


import dev.andrearaujo.model.PurchaseTransaction;
import dev.andrearaujo.repository.PurchaseTransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseTransactionService {

    private final PurchaseTransactionRepository transactionRepository;

    @Autowired
    public PurchaseTransactionService(PurchaseTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public PurchaseTransaction saveTransaction(PurchaseTransaction transaction) {
        transaction.setUuid(UUID.randomUUID());
        return transactionRepository.save(transaction);
    }

    public List<PurchaseTransaction> getAllTransactions() {
        return transactionRepository.findByDeletedFalse();
    }

    public PurchaseTransaction getTransactionById(Long id) {
        return transactionRepository.findByIdAndDeletedFalse(id).orElse(null);
    }

    public PurchaseTransaction updateTransaction(Long transactionId, PurchaseTransaction updatedTransaction) {
        PurchaseTransaction existingTransaction = this.transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + transactionId));

        existingTransaction.setDescription(updatedTransaction.getDescription());
        existingTransaction.setPurchaseAmount(updatedTransaction.getPurchaseAmount());
        existingTransaction.setTransactionDate(updatedTransaction.getTransactionDate());

        return this.transactionRepository.save(existingTransaction);
    }

    public void deleteTransaction(Long transactionId) {
        Optional<PurchaseTransaction> optionalTransaction = transactionRepository.findById(transactionId);

        if (optionalTransaction.isPresent()) {
            PurchaseTransaction transaction = optionalTransaction.get();
            transaction.setDeleted(true);
            transactionRepository.save(transaction);
        } else {
            throw new EntityNotFoundException("Transaction not found with id: " + transactionId);
        }
    }

}
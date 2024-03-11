package dev.andrearaujo.repository;

import dev.andrearaujo.model.PurchaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, Long> {
    Optional<PurchaseTransaction> findByIdAndDeletedFalse(Long id);
    List<PurchaseTransaction> findByDeletedFalse();
}

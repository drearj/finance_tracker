package dev.andrearaujo.controller;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import dev.andrearaujo.error.ValidationErrorResponse;
import dev.andrearaujo.model.PurchaseTransaction;
import dev.andrearaujo.service.PurchaseTransactionService;

@RestController
@RequestMapping("/transactions")
@Validated
public class PurchaseTransactionController {

    private final PurchaseTransactionService transactionService;

    @Autowired
    public PurchaseTransactionController(PurchaseTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public PurchaseTransaction createTransaction(@RequestBody @Valid PurchaseTransaction transaction, HttpServletResponse response) {
        response.setStatus(201);
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping
    public List<PurchaseTransaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseTransaction> getTransactionById(@PathVariable Long id) {
        PurchaseTransaction transaction = transactionService.getTransactionById(id);

        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody PurchaseTransaction updatedTransaction) {
        Optional<PurchaseTransaction> existingTransactionOptional = Optional.ofNullable(this.transactionService.getTransactionById(id));

        if (existingTransactionOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found with id: " + id);
        }

        updatedTransaction = this.transactionService.updateTransaction(id, updatedTransaction);

        return ResponseEntity.ok(updatedTransaction);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setMessage("Validation failed");

        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                response.addError(fieldError.getField(), fieldError.getDefaultMessage());
            } else {
                response.addError(error.getObjectName(), error.getDefaultMessage());
            }
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
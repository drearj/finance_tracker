package dev.andrearaujo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.andrearaujo.model.PurchaseTransaction;
import dev.andrearaujo.service.PurchaseTransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PurchaseTransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PurchaseTransactionService transactionService;

    @InjectMocks
    private PurchaseTransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    void createTransaction_ValidTransaction_ReturnsCreatedStatus() throws Exception {
        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setDescription("Test Transaction");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        transaction.setTransactionDate(dateFormat.parse("1999-07-15"));
        transaction.setPurchaseAmount(BigDecimal.TEN);

        when(transactionService.saveTransaction(any(PurchaseTransaction.class))).thenReturn(transaction);

        ResultActions result = mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(transaction)));

        result.andExpect(status().isCreated());
    }

    @Test
    void getAllTransactions_ReturnsListOfTransactions() throws Exception {
        List<PurchaseTransaction> transactions = new ArrayList<>();
        transactions.add(new PurchaseTransaction());
        transactions.add(new PurchaseTransaction());

        when(transactionService.getAllTransactions()).thenReturn(transactions);

        ResultActions result = mockMvc.perform(get("/transactions"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(transactions.size()));
    }

    @Test
    void getTransactionById_ExistingTransaction_ReturnsTransaction() throws Exception {
        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setId(1L);

        when(transactionService.getTransactionById(1L)).thenReturn(transaction);

        ResultActions result = mockMvc.perform(get("/transactions/{id}", 1));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
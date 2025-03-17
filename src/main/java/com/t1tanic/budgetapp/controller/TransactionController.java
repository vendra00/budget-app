package com.t1tanic.budgetapp.controller;

import com.t1tanic.budgetapp.dto.TransactionRequest;
import com.t1tanic.budgetapp.model.Transaction;
import com.t1tanic.budgetapp.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction API", description = "Manage financial transactions for bank accounts and investments")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @Operation(
            summary = "Create and process a transaction",
            description = "Creates a financial transaction for a bank account or investment."
    )
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createAndProcessTransaction(
                request.getType(), request.getAmount(), request.getDescription(),
                request.getFinancialOptionId(), request.getRecipientOrStockSymbol(), request.getQuantity()
        );
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    @Operation(summary = "Retrieve all transactions", description = "Fetches a list of all recorded transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{financialOptionId}")
    @Operation(
            summary = "Retrieve transactions by financial option",
            description = "Fetches transactions for a specific financial option (bank account or investment)."
    )
    public ResponseEntity<List<Transaction>> getTransactionsByFinancialOption(@PathVariable Long financialOptionId) {
        return ResponseEntity.ok(transactionService.getTransactionsByFinancialOption(financialOptionId));
    }
}

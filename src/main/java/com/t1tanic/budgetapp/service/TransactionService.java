package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.model.Transaction;
import com.t1tanic.budgetapp.model.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    Transaction createAndProcessTransaction(TransactionType type,
                                            BigDecimal amount,
                                            String description,
                                            Long financialOptionId, // Updated: Use ID to fetch financial option
                                            String recipientOrStockSymbol,
                                            Integer quantity);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByFinancialOption(Long financialOptionId);
}


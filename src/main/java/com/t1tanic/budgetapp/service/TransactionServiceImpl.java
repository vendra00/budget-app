package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.factory.TransactionFactory;
import com.t1tanic.budgetapp.model.FinancialOption;
import com.t1tanic.budgetapp.model.Transaction;
import com.t1tanic.budgetapp.model.TransactionType;
import com.t1tanic.budgetapp.repository.FinancialOptionRepository;
import com.t1tanic.budgetapp.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final FinancialOptionRepository financialOptionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, FinancialOptionRepository financialOptionRepository) {
        this.transactionRepository = transactionRepository;
        this.financialOptionRepository = financialOptionRepository;
    }

    @Override
    public Transaction createAndProcessTransaction(TransactionType type,
                                                   BigDecimal amount,
                                                   String description,
                                                   Long financialOptionId, // Updated: Use ID to fetch financial option
                                                   String recipientOrStockSymbol,
                                                   Integer quantity) {
        // Retrieve the financial option (e.g., BankAccount, Investment)
        FinancialOption financialOption = financialOptionRepository.findById(financialOptionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Financial Option ID"));

        // Create transaction using the factory
        Transaction transaction = TransactionFactory.createTransaction(
                type, amount, description, financialOption, recipientOrStockSymbol, quantity);

        // Process the transaction (e.g., deduct balance for bank transactions)
        transaction.processTransaction();

        // Save and return the transaction
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByFinancialOption(Long financialOptionId) {
        return transactionRepository.findByFinancialOptionId(financialOptionId);
    }
}


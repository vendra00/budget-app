package com.t1tanic.budgetapp.factory;

import com.t1tanic.budgetapp.model.*;

import java.math.BigDecimal;

public class TransactionFactory {

    public static Transaction createTransaction(TransactionType type,
                                                BigDecimal amount,
                                                String description,
                                                FinancialOption financialOption,
                                                String recipientOrStockSymbol,
                                                Integer quantity) {
        return switch (type) {
            case BANK_TRANSFER -> new BankTransaction(amount, description,
                    (BankAccount) financialOption,
                    recipientOrStockSymbol);
            case STOCK_BUY, STOCK_SELL -> new InvestmentTransaction(amount, description,
                    financialOption,
                    recipientOrStockSymbol,
                    quantity, type);
            default -> throw new IllegalArgumentException("Invalid transaction type");
        };
    }
}

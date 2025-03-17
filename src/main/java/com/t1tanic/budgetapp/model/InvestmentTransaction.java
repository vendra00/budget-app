package com.t1tanic.budgetapp.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class InvestmentTransaction extends Transaction {

    private String stockSymbol;
    private int quantity;

    public InvestmentTransaction(BigDecimal amount, String description,
                                 FinancialOption investment, String stockSymbol, int quantity,
                                 TransactionType transactionType) {
        super(amount, description, transactionType, investment);
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
    }

    @Override
    public void processTransaction() {
        System.out.println("Processing investment transaction: " + getTransactionType() +
                " " + quantity + " shares of " + stockSymbol +
                " for " + getAmount());
    }
}




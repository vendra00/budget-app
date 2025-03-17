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
public class BankTransaction extends Transaction {

    private String recipientAccountNumber;

    public BankTransaction(BigDecimal amount, String description,
                           BankAccount bankAccount, String recipientAccountNumber) {
        super(amount, description, TransactionType.BANK_TRANSFER, bankAccount);
        this.recipientAccountNumber = recipientAccountNumber;
    }

    @Override
    public void processTransaction() {
        BankAccount bankAccount = (BankAccount) getFinancialOption();
        bankAccount.setBalance(bankAccount.getBalance() - getAmount().doubleValue());

        System.out.println("Processing bank transfer from " + bankAccount.getAccountNumber() +
                " to " + recipientAccountNumber + " for amount " + getAmount());
    }
}




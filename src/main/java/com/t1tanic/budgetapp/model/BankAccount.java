package com.t1tanic.budgetapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends FinancialOption {

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false, unique = true)
    private String iban;

    public BankAccount(String name, double balance, User user, String accountNumber, String iban) {
        super(name, balance, user);
        this.accountNumber = accountNumber;
        this.iban = iban;
    }
}

package com.t1tanic.budgetapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@DiscriminatorValue("BANK_ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends FinancialOption {

    @NotBlank
    @Column(unique = true, nullable = false)
    private String accountNumber;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String iban;

    public BankAccount(String name, double balance, User user, String accountNumber, String iban) {
        super(null, name, balance, user);
        this.accountNumber = accountNumber;
        this.iban = iban;
    }
}

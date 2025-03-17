package com.t1tanic.budgetapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Joined Inheritance for multiple transaction types
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private LocalDateTime transactionDate = LocalDateTime.now();

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "financial_option_id", nullable = false)
    private FinancialOption financialOption;

    protected Transaction(BigDecimal amount, String description,
                          TransactionType transactionType, FinancialOption financialOption) {
        this.amount = amount;
        this.description = description;
        this.transactionType = transactionType;
        this.financialOption = financialOption;
    }

    public abstract void processTransaction();
}



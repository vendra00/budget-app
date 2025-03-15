package com.t1tanic.budgetapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "financial_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FinancialType type; // BANK_ACCOUNT, INVESTMENT, EXPENSE

    private String name;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

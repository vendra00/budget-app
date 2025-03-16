package com.t1tanic.budgetapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "financial_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private double totalBalance;

    @Column(nullable = false)
    private double totalBankAccounts;

    @Column(nullable = false)
    private double totalInvestments;

    @Column(nullable = false)
    private LocalDate recordDate;
}

package com.t1tanic.budgetapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "financial_options")
@Inheritance(strategy = InheritanceType.JOINED) // Table Per Class Strategy
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class FinancialOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

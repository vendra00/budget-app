package com.t1tanic.budgetapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "financialOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    public FinancialOption(String name, double balance, User user) {
        this.name = name;
        this.balance = balance;
        this.user = user;
    }
}

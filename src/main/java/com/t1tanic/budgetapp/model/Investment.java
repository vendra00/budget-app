package com.t1tanic.budgetapp.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "investments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investment extends FinancialOption {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestmentCategory category; // STOCK, CRYPTO, etc.

    @Positive
    @Column(nullable = false)
    private double units; // Number of stocks, crypto coins, etc.

    @Positive
    @Column(nullable = false)
    private double pricePerUnit; // Purchase price per unit

    public Investment(String name, double balance, User user, InvestmentCategory category, double pricePerUnit, double units) {
        super(name, balance, user);
        this.category = category;
        this.pricePerUnit = pricePerUnit;
        this.units = units;
    }
}

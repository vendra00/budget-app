package com.t1tanic.budgetapp.dto;

import com.t1tanic.budgetapp.model.InvestmentCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDTO {
    private Long id;
    private String name;
    private double balance;
    private InvestmentCategory category;
    private double units;
    private double pricePerUnit;
}

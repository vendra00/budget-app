package com.t1tanic.budgetapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDTO {
    private double totalBalance;
    private double totalBankAccounts;
    private double totalInvestments;
}

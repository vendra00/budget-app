package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.model.FinancialOption;
import com.t1tanic.budgetapp.model.FinancialType;

import java.util.List;

public interface FinancialOptionService {
    FinancialOption addFinancialOption(Long userId, FinancialOption option);
    List<FinancialOption> getUserFinancialOptions(Long userId);
    List<FinancialOption> getUserFinancialOptionsByType(Long userId, FinancialType type);
}

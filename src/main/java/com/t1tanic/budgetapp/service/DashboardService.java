package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.DashboardDTO;
import com.t1tanic.budgetapp.model.FinancialHistory;

import java.util.List;

public interface DashboardService {
    DashboardDTO getUserDashboard(Long userId);
    List<FinancialHistory> getUserFinancialHistory(Long userId);
    void recordDailyFinancialSnapshot();
}

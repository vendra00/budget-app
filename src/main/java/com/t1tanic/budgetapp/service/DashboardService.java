package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.DashboardDTO;
import com.t1tanic.budgetapp.model.FinancialHistory;

import java.util.List;

public interface DashboardService {

    /**
     * Get the user's dashboard information
     * @param userId the user's id
     * @return the user's dashboard information
     */
    DashboardDTO getUserDashboard(Long userId);

    /**
     * Get the user's financial history
     * @param userId the user's id
     * @return the user's financial history
     */
    List<FinancialHistory> getUserFinancialHistory(Long userId);

    /**
     * Record a daily financial snapshot (scheduled task)
     */
    void recordDailyFinancialSnapshot();
}

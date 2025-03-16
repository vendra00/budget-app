package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.model.FinancialOption;

import java.util.List;

/**
 * Service for managing user financial options.
 */
public interface FinancialOptionService {
    /**
     * Associates a new financial option with a user.
     * @param userId The user ID to associate the financial option with
     * @param option The financial option to associate with the user
     * @return The financial option that was saved
     */
    FinancialOption addFinancialOption(Long userId, FinancialOption option);

    /**
     * Retrieves all financial options associated with a user.
     * @param userId The user ID to retrieve financial options for
     * @return A list of financial options associated with the user
     */
    List<FinancialOption> getUserFinancialOptions(Long userId);
}

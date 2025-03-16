package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.InvestmentDTO;
import com.t1tanic.budgetapp.model.Investment;
import java.util.List;

/**
 * Service for managing user investments.
 */
public interface InvestmentService {
    /**
     * Associates a new investment with a user.
     * @param userId The user ID to associate the investment with
     * @param investment The investment to associate with the user
     * @return The investment that was saved
     */
    Investment addInvestment(Long userId, Investment investment);

    /**
     * Retrieves all investments associated with a user.
     * @param userId The user ID to retrieve investments for
     * @return A list of investments associated with the user
     */
    List<InvestmentDTO> getUserInvestments(Long userId);

    /**
     * Updates an existing investment for the specified user.
     * @param userId The user ID to update the investment for
     * @param investmentId The ID of the investment to update
     * @param investment The updated investment information
     * @return The updated investment
     */
    Investment updateInvestment(Long userId, Long investmentId, Investment investment);

    /**
     * Removes an investment for the specified user.
     * @param investmentId The ID of the investment to remove
     */
    void deleteInvestment(Long investmentId);
}

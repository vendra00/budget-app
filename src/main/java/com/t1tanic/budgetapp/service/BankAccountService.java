package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.BankAccountDTO;
import com.t1tanic.budgetapp.model.BankAccount;

import java.util.List;

/**
 * Service for managing user bank accounts.
 */
public interface BankAccountService {
    /**
     * Associates a new bank account with a user.
     * @param userId The user ID to associate the bank account with
     * @param bankAccount The bank account to associate with the user
     * @return The bank account that was saved
     */
    BankAccount addBankAccount(Long userId, BankAccount bankAccount);

    /**
     * Retrieves all bank accounts associated with a user.
     * @param userId The user ID to retrieve bank accounts for
     * @return A list of bank accounts associated with the user
     */
    List<BankAccountDTO> getUserBankAccounts(Long userId);

    /**
     * Updates an existing bank account for the specified user.
     * @param userId The user ID to update the bank account for
     * @param accountId The ID of the bank account to update
     * @param updatedBankAccount The updated bank account information
     * @return The updated bank account
     */
    BankAccount updateBankAccount(Long userId, Long accountId, BankAccount updatedBankAccount);

    /**
     * Removes a bank account for the specified user.
     * @param userId The user ID to remove the bank account for
     * @param accountId The ID of the bank account to remove
     */
    void deleteBankAccount(Long userId, Long accountId);
}

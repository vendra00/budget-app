package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.BankAccountDTO;
import com.t1tanic.budgetapp.model.BankAccount;

import java.util.List;

public interface BankAccountService {
    BankAccount addBankAccount(Long userId, BankAccount bankAccount);
    List<BankAccountDTO> getUserBankAccounts(Long userId);
}

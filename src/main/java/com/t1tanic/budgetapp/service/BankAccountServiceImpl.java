package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.BankAccountDTO;
import com.t1tanic.budgetapp.dto.UserDTO;
import com.t1tanic.budgetapp.model.BankAccount;
import com.t1tanic.budgetapp.model.User;
import com.t1tanic.budgetapp.repository.BankAccountRepository;
import com.t1tanic.budgetapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BankAccount addBankAccount(Long userId, BankAccount bankAccount) {
        log.info("Adding Bank Account");
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            bankAccount.setUser(user.get());
            return bankAccountRepository.save(bankAccount);  // ✅ No need to manually set type anymore!
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public List<BankAccountDTO> getUserBankAccounts(Long userId) {
        log.info("Getting Bank Accounts");
        List<BankAccount> bankAccounts = bankAccountRepository.findByUserId(userId);

        return bankAccounts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BankAccount updateBankAccount(Long userId, Long accountId, BankAccount updatedBankAccount) {
        log.info("Updating Bank Account {} for user {}", accountId, userId);
        Optional<BankAccount> existingAccount = bankAccountRepository.findById(accountId);

        if (existingAccount.isPresent()) {
            BankAccount bankAccount = existingAccount.get();
            if (!bankAccount.getUser().getId().equals(userId)) {
                throw new IllegalArgumentException("Bank account does not belong to the user");
            }
            // Update the fields
            bankAccount.setName(updatedBankAccount.getName());
            bankAccount.setBalance(updatedBankAccount.getBalance());
            bankAccount.setAccountNumber(updatedBankAccount.getAccountNumber());
            bankAccount.setIban(updatedBankAccount.getIban());

            return bankAccountRepository.save(bankAccount);
        }
        throw new IllegalArgumentException("Bank account not found");
    }

    @Override
    public void deleteBankAccount(Long userId, Long accountId) {
        log.info("Deleting Bank Account {} for user {}", accountId, userId);
        Optional<BankAccount> existingAccount = bankAccountRepository.findById(accountId);

        if (existingAccount.isPresent()) {
            BankAccount bankAccount = existingAccount.get();
            if (!bankAccount.getUser().getId().equals(userId)) {
                throw new IllegalArgumentException("Bank account does not belong to the user");
            }
            bankAccountRepository.delete(bankAccount);
        } else {
            throw new IllegalArgumentException("Bank account not found");
        }
    }

    private BankAccountDTO convertToDTO(BankAccount bankAccount) {
        UserDTO userDTO = new UserDTO(bankAccount.getUser().getId(), bankAccount.getUser().getEmail());
        return new BankAccountDTO(
                bankAccount.getId(),
                bankAccount.getName(),
                bankAccount.getBalance(),
                bankAccount.getAccountNumber(),
                bankAccount.getIban(),
                userDTO
        );
    }
}

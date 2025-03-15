package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.BankAccountDTO;
import com.t1tanic.budgetapp.dto.UserDTO;
import com.t1tanic.budgetapp.model.BankAccount;
import com.t1tanic.budgetapp.model.User;
import com.t1tanic.budgetapp.repository.BankAccountRepository;
import com.t1tanic.budgetapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            bankAccount.setUser(user.get());
            return bankAccountRepository.save(bankAccount);
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public List<BankAccountDTO> getUserBankAccounts(Long userId) {
        List<BankAccount> bankAccounts = bankAccountRepository.findByUserId(userId);

        return bankAccounts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BankAccountDTO convertToDTO(BankAccount bankAccount) {
        UserDTO userDTO = new UserDTO(bankAccount.getUser().getId(), bankAccount.getUser().getEmail());
        return new BankAccountDTO(
                bankAccount.getId(),
                bankAccount.getBankName(),
                bankAccount.getAccountNumber(),
                bankAccount.getIban(),
                bankAccount.getBalance(),
                userDTO
        );
    }
}

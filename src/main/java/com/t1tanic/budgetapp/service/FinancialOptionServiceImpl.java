package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.model.FinancialOption;
import com.t1tanic.budgetapp.model.User;
import com.t1tanic.budgetapp.repository.FinancialOptionRepository;
import com.t1tanic.budgetapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FinancialOptionServiceImpl implements FinancialOptionService {

    private final FinancialOptionRepository financialOptionRepository;
    private final UserRepository userRepository;

    public FinancialOptionServiceImpl(FinancialOptionRepository financialOptionRepository, UserRepository userRepository) {
        this.financialOptionRepository = financialOptionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FinancialOption addFinancialOption(Long userId, FinancialOption option) {
        log.info("addFinancialOption");
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            option.setUser(user.get());
            return financialOptionRepository.save(option);
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public List<FinancialOption> getUserFinancialOptions(Long userId) {
        return financialOptionRepository.findByUserId(userId);
    }
}

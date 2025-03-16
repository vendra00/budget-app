package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.DashboardDTO;
import com.t1tanic.budgetapp.model.BankAccount;
import com.t1tanic.budgetapp.model.FinancialHistory;
import com.t1tanic.budgetapp.model.Investment;
import com.t1tanic.budgetapp.model.User;
import com.t1tanic.budgetapp.repository.BankAccountRepository;
import com.t1tanic.budgetapp.repository.FinancialHistoryRepository;
import com.t1tanic.budgetapp.repository.InvestmentRepository;
import com.t1tanic.budgetapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final InvestmentRepository investmentRepository;
    private final FinancialHistoryRepository financialHistoryRepository;

    public DashboardServiceImpl(UserRepository userRepository, BankAccountRepository bankAccountRepository, InvestmentRepository investmentRepository, FinancialHistoryRepository financialHistoryRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.investmentRepository = investmentRepository;
        this.financialHistoryRepository = financialHistoryRepository;
    }

    @Override
    public DashboardDTO getUserDashboard(Long userId) {
        log.info("getUserDashboard");
        // Check if the user exists
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            throw new IllegalArgumentException("Invalid or Non-existing User");
        }

        // Fetch all bank accounts
        List<BankAccount> bankAccounts = bankAccountRepository.findByUserId(userId);
        double totalBankAccounts = bankAccounts.stream().mapToDouble(BankAccount::getBalance).sum();

        // Fetch all investments
        List<Investment> investments = investmentRepository.findByUserId(userId);
        double totalInvestments = investments.stream().mapToDouble(Investment::getBalance).sum();

        // Calculate total balance
        double totalBalance = totalBankAccounts + totalInvestments;

        return new DashboardDTO(totalBalance, totalBankAccounts, totalInvestments);
    }

    @Override
    public List<FinancialHistory> getUserFinancialHistory(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Invalid or Non-existing User");
        }
        return financialHistoryRepository.findByUserIdOrderByRecordDateDesc(userId);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    public void recordDailyFinancialSnapshot() {
        log.info("recordDailyFinancialSnapshot");
        List<User> users = userRepository.findAll();
        LocalDate today = LocalDate.now();

        for (User user : users) {
            DashboardDTO dashboard = getUserDashboard(user.getId());

            FinancialHistory history = new FinancialHistory();
            history.setUser(user);
            history.setTotalBalance(dashboard.getTotalBalance());
            history.setTotalBankAccounts(dashboard.getTotalBankAccounts());
            history.setTotalInvestments(dashboard.getTotalInvestments());
            history.setRecordDate(today);

            financialHistoryRepository.save(history);
        }
    }
}

package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.InvestmentDTO;
import com.t1tanic.budgetapp.model.Investment;
import com.t1tanic.budgetapp.model.User;
import com.t1tanic.budgetapp.repository.InvestmentRepository;
import com.t1tanic.budgetapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final UserRepository userRepository;

    public InvestmentServiceImpl(InvestmentRepository investmentRepository, UserRepository userRepository) {
        this.investmentRepository = investmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Investment addInvestment(Long userId, Investment investment) {
        log.info("Adding Investment");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        investment.setUser(user);
        return investmentRepository.save(investment);
    }

    @Override
    public List<InvestmentDTO> getUserInvestments(Long userId) {
        log.info("Fetching Investments for user {}", userId);
        List<Investment> investments = investmentRepository.findByUserId(userId);
        return investments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Investment updateInvestment(Long userId, Long investmentId, Investment investment) {
        log.info("Updating Investment {} for user {}", investmentId, userId);

        Investment existingInvestment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new IllegalArgumentException("Investment not found"));

        // Ensure that the investment belongs to the correct user
        if (!existingInvestment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Investment does not belong to the user");
        }

        // Update the financial option details (which Investment extends)
        existingInvestment.setName(investment.getName());
        existingInvestment.setBalance(investment.getBalance());

        // Update investment-specific details
        existingInvestment.setCategory(investment.getCategory());
        existingInvestment.setUnits(investment.getUnits());
        existingInvestment.setPricePerUnit(investment.getPricePerUnit());

        return investmentRepository.save(existingInvestment);
    }


    @Override
    public void deleteInvestment(Long investmentId) {
        log.info("Deleting Investment {}", investmentId);
        investmentRepository.deleteById(investmentId);
    }

    private InvestmentDTO convertToDTO(Investment investment) {
        return new InvestmentDTO(
                investment.getId(),
                investment.getName(),
                investment.getBalance(),
                investment.getCategory(),
                investment.getUnits(),
                investment.getPricePerUnit()
        );
    }
}

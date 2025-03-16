package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.dto.InvestmentDTO;
import com.t1tanic.budgetapp.model.Investment;
import java.util.List;

public interface InvestmentService {
    Investment addInvestment(Long userId, Investment investment);
    List<InvestmentDTO> getUserInvestments(Long userId);
    Investment updateInvestment(Long userId, Long investmentId, Investment investment);
    void deleteInvestment(Long investmentId);
}

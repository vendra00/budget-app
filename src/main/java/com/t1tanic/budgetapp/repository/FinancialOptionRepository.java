package com.t1tanic.budgetapp.repository;

import com.t1tanic.budgetapp.model.FinancialOption;
import com.t1tanic.budgetapp.model.FinancialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialOptionRepository extends JpaRepository<FinancialOption, Long> {
    List<FinancialOption> findByUserId(Long userId); // Get all financial options for a user
    List<FinancialOption> findByUserIdAndType(Long userId, FinancialType type); // Filter by type (bank, investment, expense)
}

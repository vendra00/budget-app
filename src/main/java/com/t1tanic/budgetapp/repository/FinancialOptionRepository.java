package com.t1tanic.budgetapp.repository;

import com.t1tanic.budgetapp.model.FinancialOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialOptionRepository extends JpaRepository<FinancialOption, Long> {
    List<FinancialOption> findByUserId(Long userId);
}

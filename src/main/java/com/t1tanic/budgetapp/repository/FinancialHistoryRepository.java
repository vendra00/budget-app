package com.t1tanic.budgetapp.repository;

import com.t1tanic.budgetapp.model.FinancialHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialHistoryRepository extends JpaRepository<FinancialHistory, Long> {
    List<FinancialHistory> findByUserIdOrderByRecordDateDesc(Long userId);
}

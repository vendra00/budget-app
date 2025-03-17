package com.t1tanic.budgetapp.dto;

import com.t1tanic.budgetapp.model.TransactionType;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private Long financialOptionId;
    private String recipientOrStockSymbol;
    private Integer quantity;
}


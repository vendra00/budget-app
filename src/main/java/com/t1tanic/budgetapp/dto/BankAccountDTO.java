package com.t1tanic.budgetapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private Long id;
    private String bankName;
    private String accountNumber;
    private String iban;
    private Double balance;
    private UserDTO user; // Only return basic user details
}

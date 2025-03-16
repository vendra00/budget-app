package com.t1tanic.budgetapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private Long id;
    private String name;
    private double balance;
    private String accountNumber;
    private String iban;
    private UserDTO user;
}

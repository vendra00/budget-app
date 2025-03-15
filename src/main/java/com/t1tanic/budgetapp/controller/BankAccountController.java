package com.t1tanic.budgetapp.controller;

import com.t1tanic.budgetapp.dto.BankAccountDTO;
import com.t1tanic.budgetapp.model.BankAccount;
import com.t1tanic.budgetapp.service.BankAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-accounts")
@Tag(name = "Bank Account API", description = "Manage user bank accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/{userId}")
    @Operation(summary = "Add a bank account", description = "Associates a new bank account with a user.")
    public ResponseEntity<BankAccount> addBankAccount(@PathVariable Long userId, @Valid @RequestBody BankAccount bankAccount) {
        BankAccount savedAccount = bankAccountService.addBankAccount(userId, bankAccount);
        return ResponseEntity.ok(savedAccount);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user's bank accounts", description = "Retrieves all bank accounts associated with a user.")
    public ResponseEntity<List<BankAccountDTO>> getUserBankAccounts(@PathVariable Long userId) {
        List<BankAccountDTO> accounts = bankAccountService.getUserBankAccounts(userId);
        return ResponseEntity.ok(accounts);
    }
}

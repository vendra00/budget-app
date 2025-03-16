package com.t1tanic.budgetapp.controller;

import com.t1tanic.budgetapp.dto.InvestmentDTO;
import com.t1tanic.budgetapp.model.Investment;
import com.t1tanic.budgetapp.service.InvestmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investments")
@Tag(name = "Investment API", description = "Manage user investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping("/{userId}")
    @Operation(summary = "Add an investment", description = "Associates a new investment with a user.")
    public ResponseEntity<Investment> addInvestment(@PathVariable Long userId, @Valid @RequestBody Investment investment) {
        Investment savedInvestment = investmentService.addInvestment(userId, investment);
        return ResponseEntity.ok(savedInvestment);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user's investments")
    public ResponseEntity<List<InvestmentDTO>> getUserInvestments(@PathVariable Long userId) {
        List<InvestmentDTO> investments = investmentService.getUserInvestments(userId);
        return ResponseEntity.ok(investments);
    }

    @PutMapping("/{userId}/{investmentId}")
    @Operation(summary = "Update investment", description = "Updates an existing investment for the specified user.")
    public ResponseEntity<Investment> updateInvestment(@PathVariable Long userId, @PathVariable Long investmentId, @RequestBody Investment investment) {
        Investment updatedInvestment = investmentService.updateInvestment(userId, investmentId, investment);
        return ResponseEntity.ok(updatedInvestment);
    }

    @DeleteMapping("/{investmentId}")
    @Operation(summary = "Delete an investment")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long investmentId) {
        investmentService.deleteInvestment(investmentId);
        return ResponseEntity.noContent().build();
    }
}

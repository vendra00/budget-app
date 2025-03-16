package com.t1tanic.budgetapp.controller;

import com.t1tanic.budgetapp.dto.DashboardDTO;
import com.t1tanic.budgetapp.model.FinancialHistory;
import com.t1tanic.budgetapp.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Dashboard API", description = "Manage user dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get a user's financial dashboard",
            description = "Retrieves the total balance, total in bank accounts, and total in investments for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the dashboard"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> getUserDashboard(@PathVariable Long userId) {
        try {
            DashboardDTO dashboard = dashboardService.getUserDashboard(userId);
            return ResponseEntity.ok(dashboard);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Invalid or Non-existing User\"}");
        }
    }

    @Operation(summary = "Get a user's financial history",
            description = "Retrieves historical financial snapshots for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved history"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{userId}/history")
    public ResponseEntity<?> getUserFinancialHistory(@PathVariable Long userId) {
        List<FinancialHistory> history = dashboardService.getUserFinancialHistory(userId);
        if (history.isEmpty()) {
            return ResponseEntity.status(404).body("{\"message\": \"No financial history found for this user.\"}");
        }
        return ResponseEntity.ok(history);
    }

}

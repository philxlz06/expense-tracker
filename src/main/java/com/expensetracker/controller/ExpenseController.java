package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpensePredictionService;
import com.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
@Validated
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpensePredictionService predictionService;

    @Autowired
    public ExpenseController(ExpenseService expenseService,
            ExpensePredictionService predictionService) {
        this.expenseService = expenseService;
        this.predictionService = predictionService;
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense,
            @RequestParam Long userId,
            @RequestParam Long categoryId) {
        Expense createdExpense = expenseService.createExpense(expense, userId, categoryId);
        return ResponseEntity.ok(createdExpense);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        return expense.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔥 New ML Prediction Endpoint
    @GetMapping("/predict")
    public ResponseEntity<String> predict(@RequestParam String description) {
        String category = predictionService.getPredictedCategory(description);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,
            @Valid @RequestBody Expense updatedExpense,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long categoryId) {
        Optional<Expense> expenseOptional = expenseService.getExpenseById(id);
        if (expenseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Expense expense = expenseService.updateExpense(id, updatedExpense, userId, categoryId);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Optional<Expense> expenseOptional = expenseService.getExpenseById(id);
        if (expenseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}

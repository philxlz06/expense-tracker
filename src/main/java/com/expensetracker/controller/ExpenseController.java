package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Create expense with userId and categoryId as request parameters
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense,
                                                 @RequestParam Long userId,
                                                 @RequestParam Long categoryId) {
        Expense createdExpense = expenseService.createExpense(expense, userId, categoryId);
        return ResponseEntity.ok(createdExpense);
    }

    // Get all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    // Get expense by id
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        return expense.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update expense by id (userId and categoryId are optional params if you want to update these)
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,
                                                 @RequestBody Expense updatedExpense,
                                                 @RequestParam(required = false) Long userId,
                                                 @RequestParam(required = false) Long categoryId) {
        Expense expense = expenseService.updateExpense(id, updatedExpense, userId, categoryId);
        return ResponseEntity.ok(expense);
    }

    // Delete expense by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}

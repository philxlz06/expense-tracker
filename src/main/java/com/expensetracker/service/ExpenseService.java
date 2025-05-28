package com.expensetracker.service;

import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    public ExpenseRepository getExpenseRepository() {
        return expenseRepository;
    }

    public void setExpenseRepository(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    expense.setDescription(updatedExpense.getDescription());
                    expense.setAmount(updatedExpense.getAmount());
                    expense.setExpenseDate(updatedExpense.getExpenseDate());
                    return expenseRepository.save(expense);
                })
                .orElseGet(() -> {
                    updatedExpense.setId(id);
                    return expenseRepository.save(updatedExpense);
                });
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}

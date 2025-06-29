package com.expensetracker.service;

import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;
import com.expensetracker.model.User;
import com.expensetracker.repository.CategoryRepository;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense createExpense(Expense expense, Long userId, Long categoryId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }

        User user = userService.getUserByIdOrThrow(userId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        expense.setUser(user);
        expense.setCategory(category);

        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense, Long userId, Long categoryId) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    expense.setDescription(updatedExpense.getDescription());
                    expense.setAmount(updatedExpense.getAmount());
                    expense.setExpenseDate(updatedExpense.getExpenseDate());

                    if (userId != null) {
                        User user = userService.getUserByIdOrThrow(userId);
                        expense.setUser(user);
                    }
                    if (categoryId != null) {
                        Category category = categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
                        expense.setCategory(category);
                    }
                    return expenseRepository.save(expense);
                })
                .orElseThrow(() -> new IllegalArgumentException("Expense with id " + id + " not found"));
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}

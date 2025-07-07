package com.expensetracker.dto;

public class ExpenseDescriptionRequest {
    private String description;

    public ExpenseDescriptionRequest() {
    }

    public ExpenseDescriptionRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

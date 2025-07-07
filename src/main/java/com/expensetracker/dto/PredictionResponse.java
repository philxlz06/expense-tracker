package com.expensetracker.dto;

public class PredictionResponse {
    private String predicted_category;

    public String getPredicted_category() {
        return predicted_category;
    }

    public void setPredicted_category(String predicted_category) {
        this.predicted_category = predicted_category;
    }
}

package com.expensetracker.service;

import com.expensetracker.dto.ExpenseDescriptionRequest;
import com.expensetracker.dto.PredictionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExpensePredictionService {

    @Autowired
    private RestTemplate restTemplate;

    public String getPredictedCategory(String description) {
        ExpenseDescriptionRequest request = new ExpenseDescriptionRequest(description);

        PredictionResponse response = restTemplate.postForObject(
                "http://localhost:8000/predict",
                request,
                PredictionResponse.class);

        return response != null ? response.getPredicted_category() : "Unknown";
    }
}

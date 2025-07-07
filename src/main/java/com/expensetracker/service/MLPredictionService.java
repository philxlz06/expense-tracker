package com.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;

@Service
public class MLPredictionService {

    private final RestTemplate restTemplate;

    @Autowired
    public MLPredictionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String predictCategory(String description) {
        String url = "http://localhost:8000/predict"; // Replace with your EC2 URL if deployed

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payload = Map.of("description", description);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return response.getBody().get("predicted_category").toString();
    }
}

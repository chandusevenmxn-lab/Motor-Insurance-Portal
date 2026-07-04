package com.example.motor_insurance_portal.quote.controller;

import com.example.motor_insurance_portal.quote.model.Quote;
import com.example.motor_insurance_portal.quote.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/motor/v1")
public class ExperienceController {

    @Autowired
    private QuoteRepository quoteRepository;

    @PostMapping("/quotes")
    public Quote createQuote(@RequestBody Quote quoteRequest) {
        quoteRequest.setStatus("DRAFT");
        // Persisting to Azure SQL via JPA
        return quoteRepository.save(quoteRequest);
    }

    @GetMapping("/quotes")
    public List<Quote> listQuotes() {
        return quoteRepository.findAll();
    }

    @GetMapping("/quotes/{id}")
    public Quote getQuote(@PathVariable UUID id) {
        return quoteRepository.findById(id).orElseThrow();
    }
}

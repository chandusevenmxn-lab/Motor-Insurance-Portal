package com.example.motor_insurance_portal.quotation;

import com.example.motor_insurance_portal.quote.model.Quote;
import com.example.motor_insurance_portal.quote.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/quotation/v1/motor")
public class QuotationController {

    @Autowired
    private QuoteRepository quoteRepository;

    @PostMapping("/quotes")
    public ResponseEntity<QuoteSummaryResponse> calculatePremium(@RequestBody String quoteIdBody) {
        // The client may POST a raw string; strip quotes if present
        String idStr = quoteIdBody == null ? "" : quoteIdBody.trim();
        if (idStr.startsWith("\"") && idStr.endsWith("\"")) {
            idStr = idStr.substring(1, idStr.length() - 1);
        }
        try {
            UUID id = UUID.fromString(idStr);
            Optional<Quote> qOpt = quoteRepository.findById(id);
            if (qOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Quote q = qOpt.get();
            // simple premium calc: 5% of vehicle value
            double premium = 0.0;
            if (q.getVehicleValue() != null) premium = q.getVehicleValue() * 0.05;
            q.setTotalPremium(premium);
            q.setStatus("QUOTED");
            quoteRepository.save(q);

            QuoteSummaryResponse resp = new QuoteSummaryResponse(q.getId(), premium, q.getStatus());
            return ResponseEntity.ok(resp);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/quotes")
    public ResponseEntity<QuoteSummaryResponse> getQuoteSummary(@RequestParam("id") String idParam) {
        if (idParam == null) return ResponseEntity.badRequest().build();
        String idStr = idParam.trim();
        try {
            UUID id = UUID.fromString(idStr);
            Optional<Quote> qOpt = quoteRepository.findById(id);
            if (qOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Quote q = qOpt.get();
            double premium = 0.0;
            if (q.getTotalPremium() != null) {
                premium = q.getTotalPremium();
            } else if (q.getVehicleValue() != null) {
                premium = q.getVehicleValue() * 0.05;
            }
            String status = q.getStatus() == null ? "DRAFT" : q.getStatus();
            QuoteSummaryResponse resp = new QuoteSummaryResponse(q.getId(), premium, status);
            return ResponseEntity.ok(resp);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

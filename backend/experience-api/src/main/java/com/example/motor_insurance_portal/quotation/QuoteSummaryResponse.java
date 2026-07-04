package com.example.motor_insurance_portal.quotation;

import java.util.UUID;

public class QuoteSummaryResponse {
    private UUID id;
    private double totalPremium;
    private String status;

    public QuoteSummaryResponse() {}

    public QuoteSummaryResponse(UUID id, double totalPremium, String status) {
        this.id = id;
        this.totalPremium = totalPremium;
        this.status = status;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public double getTotalPremium() { return totalPremium; }
    public void setTotalPremium(double totalPremium) { this.totalPremium = totalPremium; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

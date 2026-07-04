package com.example.motor_insurance_portal.quote.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String registrationNumber;
    private String make;
    private String model;
    private Integer manufacturingYear;
    private Double vehicleValue;
    
    private String status; // DRAFT, QUOTED, ISSUED
    private Double totalPremium;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Integer getManufacturingYear() { return manufacturingYear; }
    public void setManufacturingYear(Integer manufacturingYear) { this.manufacturingYear = manufacturingYear; }
    public Double getVehicleValue() { return vehicleValue; }
    public void setVehicleValue(Double vehicleValue) { this.vehicleValue = vehicleValue; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getTotalPremium() { return totalPremium; }
    public void setTotalPremium(Double totalPremium) { this.totalPremium = totalPremium; }
}

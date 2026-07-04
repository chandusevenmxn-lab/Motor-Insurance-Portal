package com.example.motor_insurance_portal.quote.repository;

import com.example.motor_insurance_portal.quote.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface QuoteRepository extends JpaRepository<Quote, UUID> {
}

package com.example.motor_insurance_portal.quote;

import com.example.motor_insurance_portal.quote.model.Quote;
import com.example.motor_insurance_portal.quote.repository.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuoteServiceTest {

    @Autowired
    private QuoteRepository quoteRepository;

    @Test
    public void testCreateQuote() {
        Quote quote = new Quote();
        quote.setRegistrationNumber("MEX-1234");
        quote.setMake("Toyota");
        
        Quote savedQuote = quoteRepository.save(quote);
        assertNotNull(savedQuote.getId());
        assertEquals("Toyota", savedQuote.getMake());
    }
}

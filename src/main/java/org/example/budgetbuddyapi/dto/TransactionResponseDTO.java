package org.example.budgetbuddyapi.dto;
import java.math.BigDecimal;
import java.time.LocalDate;


// dto/ → what the data looks like in the API (JSON in and out)
// Defines what JSON comes into and out of the API.


public record TransactionResponseDTO(
        String id,
        Long accountId,
        BigDecimal amount,
        LocalDate bookedAt,
        String description
) {}
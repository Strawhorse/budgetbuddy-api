package org.example.budgetbuddyapi.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;



// dto/ → what the data looks like in the API (JSON in and out)
// Defines what JSON comes into and out of the API.


public record TransactionCreateDTO (
        @NotNull Long accountId, @Size(max = 255) String description, @NotNull LocalDate bookedAt, @NotNull BigDecimal amount
) {}

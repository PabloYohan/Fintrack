package br.com.fintrack.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private String description;
    private Money value;
    private LocalDate expectedDate;
    private final TransactionType type;

    private Transaction(UUID id, String description, Money value, LocalDate expectedDate, TransactionType type){
        this.id = id;
        this.description = description.strip();
        this.value = value;
        this.expectedDate = expectedDate;
        this.type = type;
    }
}

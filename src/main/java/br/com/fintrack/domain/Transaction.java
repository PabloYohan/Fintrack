package br.com.fintrack.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private String description;
    private Money value;
    private LocalDate expectedDate;
    private final TransactionType type;

    private Transaction(UUID id, String description, Money value, LocalDate expectedDate, TransactionType type){
        this.id = Objects.requireNonNull(id, "O id não pode ser nulo");
        String normalizedDescription = Objects.requireNonNull(description, "description não pode ser nula").strip();
        if(normalizedDescription.isBlank()){
            throw new IllegalArgumentException("description não pode estar em branco");
        }
        this.description = normalizedDescription;
        Money validatedValue = Objects.requireNonNull(value, "value não pode ser nulo");
        if(!validatedValue.isGreaterThan(Money.of("0.00"))) {
            throw new IllegalArgumentException("O valor deve ser maior que 0");
        }
        this.value = validatedValue;
        this.expectedDate = Objects.requireNonNull(expectedDate, "expectedDate não pode ser nulo");
        this.type = Objects.requireNonNull(type, "type não pode ser nulo");
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Money getValue() {
        return value;
    }

    public LocalDate getExpectedDate() {
        return expectedDate;
    }

    public TransactionType getType() {
        return type;
    }

    public static Transaction create(String description, Money value, LocalDate expectedDate, TransactionType type) {
        return new Transaction(
                UUID.randomUUID(),
                description,
                value,
                expectedDate,
                type
        );
    }
}

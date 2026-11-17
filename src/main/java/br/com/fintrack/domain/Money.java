package br.com.fintrack.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.UNNECESSARY);
    }

    public static Money of(String amount) {
        return new Money(new BigDecimal(Objects.requireNonNull(amount, "amount")));
    }

    public BigDecimal amount() {
        return amount;
    }

    public Money add(Money other) {
        return new Money(amount.add(Objects.requireNonNull(other, "other").amount));
    }

    public Money subtract(Money other) {
        return new Money(amount.subtract(Objects.requireNonNull(other, "other").amount));
    }

    public boolean isGreaterThan(Money other) {
        return amount.compareTo(Objects.requireNonNull(other).amount) > 0;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Money money && amount.equals(money.amount));
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}

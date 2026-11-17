package br.com.fintrack.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void normalizesPositiveZeroAndNegativeValuesWithoutRounding() {
        assertEquals(new BigDecimal("12.30"), Money.of("12.3").amount());
        assertEquals(new BigDecimal("0.00"), Money.of("0").amount());
        assertEquals(new BigDecimal("-0.01"), Money.of("-0.010").amount());
    }

    @Test
    void addsAndSubtractsWithoutChangingOperands() {
        Money first = Money.of("10.20");
        Money second = Money.of("-2.30");

        assertEquals(Money.of("7.90"), first.add(second));
        assertEquals(Money.of("12.50"), first.subtract(second));
        assertEquals(Money.of("10.20"), first);
        assertEquals(Money.of("-2.30"), second);
    }

    @Test
    void shouldCompareMonetaryValues(){
        Money greater = Money.of("100.00");
        Money smaller = Money.of("50.00");
        Money equal = Money.of("100.0");

        assertTrue(greater.isGreaterThan(smaller));
        assertFalse(greater.isGreaterThan(equal));
        assertFalse(smaller.isGreaterThan(greater));
    }

    @Test
    void shouldCompareNegativeValues(){
        Money first = Money.of("-10.00");
        Money second = Money.of("-20.00");
        Money zero = Money.of("0");

        assertTrue(first.isGreaterThan(second));
        assertTrue(zero.isGreaterThan(first));
        assertFalse(second.isGreaterThan(zero));
    }

    @Test
    void equalityAndHashCodeUseNormalizedValue() {
        Money first = Money.of("1.0");
        Money second = Money.of("1.000");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, Money.of("1.01"));
        assertNotEquals(first, null);
        assertNotEquals(first, "1.00");
    }

    @Test
    void rejectsValuesThatRequireRounding() {
        assertThrows(ArithmeticException.class, () -> Money.of("1.001"));
        assertThrows(ArithmeticException.class, () -> Money.of("-0.001"));
    }

    @Test
    void rejectsInvalidStringsAndNullArguments() {
        assertThrows(NumberFormatException.class, () -> Money.of(""));
        assertThrows(NumberFormatException.class, () -> Money.of("1,00"));
        assertThrows(NumberFormatException.class, () -> Money.of("abc"));
        assertThrows(NullPointerException.class, () -> Money.of(null));
        assertThrows(NullPointerException.class, () -> Money.of("1").add(null));
        assertThrows(NullPointerException.class, () -> Money.of("1").subtract(null));
    }
}

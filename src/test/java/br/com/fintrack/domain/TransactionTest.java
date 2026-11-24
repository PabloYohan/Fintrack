package br.com.fintrack.domain;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class TransactionTest {
    @Test
    void shouldCreateNewTransaction(){
        Transaction transaction = Transaction.create("  Internet  ", Money.of("120.00"),
                LocalDate.of(2026, 11, 20), TransactionType.EXPENSE);

        assertNotNull(transaction.getId());
        assertEquals("Internet", transaction.getDescription());
        assertEquals(Money.of("120.00"), transaction.getValue());
        assertEquals(LocalDate.of(2026, 11, 20), transaction.getExpectedDate());
        assertEquals(TransactionType.EXPENSE, transaction.getType());
    }

    @Test
    void rejectsTransactionWithValueZeroOrNegative(){
        assertThrows(IllegalArgumentException.class, () -> Transaction. create("  Internet  ", Money.of("0.00"),
                LocalDate.of(2026, 11, 20), TransactionType.EXPENSE));
        assertThrows(IllegalArgumentException.class, () -> Transaction. create("  Internet  ", Money.of("-120.00"),
                LocalDate.of(2026, 11, 20), TransactionType.EXPENSE));
    }

    @Test
    void descriptionShouldNotBeBlank(){
        assertThrows(IllegalArgumentException.class, () -> Transaction. create("", Money.of("120.00"),
                LocalDate.of(2026, 11, 20), TransactionType.EXPENSE));
        assertThrows(IllegalArgumentException.class, () -> Transaction. create("       ", Money.of("120.00"),
                LocalDate.of(2026, 11, 20), TransactionType.EXPENSE));
    }

    @Test
    void differentTransactionsShouldNotHaveSameId(){
        Transaction transactionOne = Transaction.create("  Internet  ", Money.of("120.00"),
                LocalDate.of(2026, 11, 20), TransactionType.EXPENSE);
        Transaction transactionTwo = Transaction.create("  Internet  ", Money.of("120.00"),
                LocalDate.of(2026, 11, 20), TransactionType.EXPENSE);

        assertNotEquals(transactionOne.getId(), transactionTwo.getId());
    }
}

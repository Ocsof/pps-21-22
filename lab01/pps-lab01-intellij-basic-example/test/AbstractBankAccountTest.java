import lab01.example.model.AccountHolder;
import lab01.example.model.StandardAccountHolder;
import lab01.example.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractBankAccountTest {
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    @BeforeEach
    public abstract void beforeEach();

    @Test
    public void testInitialBalance() {
        assertEquals(0, this.bankAccount.getBalance());
    }

    @Test
    public void testNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> this.bankAccount.deposit(this.accountHolder.getId(), -100));
        assertThrows(IllegalArgumentException.class, () -> this.bankAccount.deposit(2, -100));
    }

    @Test
    public void testNegativeWithdraw() {
        assertThrows(IllegalArgumentException.class, () -> this.bankAccount.withdraw(this.accountHolder.getId(), -70));
    }
}

import lab01.example.model.SimpleBankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;
import lab01.example.model.StandardAccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBankAccountWithAtmTest extends AbstractBankAccountTest{

    private static final int FEE = 1;

    @Override
    @BeforeEach
    public void beforeEach(){
        this.accountHolder = new StandardAccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new SimpleBankAccountWithAtm(this.accountHolder, 0);
    }

    @Test
    public void testDeposit() {
        this.bankAccount.deposit(this.accountHolder.getId(), 100);
        assertEquals(100 - FEE, this.bankAccount.getBalance());
    }

    @Test
    public void testWrongDeposit() {
        this.bankAccount.deposit(this.accountHolder.getId(), 100);
        this.bankAccount.deposit(2, 50);
        assertEquals(100 - FEE, this.bankAccount.getBalance());
    }

    @Test
    public void testWithdraw() {
        this.bankAccount.deposit(this.accountHolder.getId(), 100);
        this.bankAccount.withdraw(this.accountHolder.getId(), 70);
        assertEquals(30 - FEE - FEE, this.bankAccount.getBalance());
    }

    @Test
    public void testWrongWithdraw() {
        this.bankAccount.deposit(this.accountHolder.getId(), 100);
        this.bankAccount.withdraw(2, 70);
        assertEquals(100 - FEE, this.bankAccount.getBalance());
    }
}

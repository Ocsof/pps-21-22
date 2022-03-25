package lab01.example.model;

public abstract class AbstractBankAccount implements BankAccount {
    private final AccountHolder holder;
    private double balance;

    public AbstractBankAccount(final double balance, final AccountHolder holder) {
        this.balance = balance;
        this.holder = holder;
    }

    @Override
    public AccountHolder getHolder() {
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public final void deposit(final int userID, final double amount) {
        checkNegativeArgument(amount);
        if (checkUser(userID)) {
            this.balance = this.balance + amount - getFee();
        }
    }

    protected abstract int getFee();

    private void checkNegativeArgument(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public final void withdraw(final int userID, final double amount) {
        checkNegativeArgument(amount);
        if (checkUser(userID) && isWithdrawAllowed(amount)) {
            this.balance = this.balance - amount - getFee();
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance >= amount;
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}

package mayton;

public class Account {

    private int balance;

    public boolean withdraw(int amount) {
        if (balance < amount) {
            return false;
        }
        balance = balance - amount;
        return true;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

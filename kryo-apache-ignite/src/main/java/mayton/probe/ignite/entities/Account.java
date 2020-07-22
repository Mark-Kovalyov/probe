package mayton.probe.ignite.entities;

public class Account {

    private int currentBalance;

    public int balance() {
        return currentBalance;
    }

    public void update(int balance) {
        currentBalance += balance;
    }
}

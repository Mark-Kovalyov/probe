package mayton;

public class Main {

    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);

        account.withdraw(2500);
    }

}

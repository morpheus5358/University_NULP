package Lab1.Task7;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final List<BankAccount> accounts = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void addAccount(BankAccount account) { //перевірка на унік акаунт
        for (int i = 0; i < accounts.size(); i++) {
            BankAccount acc = accounts.get(i);
            if (acc.getAccountNumber().equals(account.getAccountNumber())) {
                throw new IllegalArgumentException("Account number must be unique");
            }
        }
        accounts.add(account);
    }
    public void printBalances() {
        System.out.println("User: " + name);
        for (int i = 0; i < accounts.size(); i++) {
            BankAccount acc = accounts.get(i);
            System.out.println("Account " + acc.getAccountNumber() + ": " + acc.getBalance() + " " + acc.getCurrency());
        }
    }

}

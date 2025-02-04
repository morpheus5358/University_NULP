package Lab1.Task7;


public class ForWork {

    public static void main(String[] args) {
        try {
            Bank bank1 = new Bank("BankofAmerica");

            Bank bank2 = new Bank("Wright-Patt");


            BankAccount acc1 = new BankAccount("111111", bank1, "USD", 1000);

            BankAccount acc2 = new BankAccount("222222", bank1, "UAH", 500);

            BankAccount acc3 = new BankAccount("333333", bank2, "EUR", 2000);

            BankAccount acc4 = new BankAccount("444444", bank2, "EUR", 1500);




            User user1 = new User("Jorge");

            User user2 = new User("Jeremy");



            user1.addAccount(acc1);
            user1.addAccount(acc2);

            user2.addAccount(acc3);
            user2.addAccount(acc4);

            Transiaction transaction = new Transiaction();

            user1.printBalances();
            user2.printBalances();

            // тут оголошуємо перормтранфвер
            performTransfer(acc1, acc2, 100, transaction);
            performTransfer(acc1, acc3, 200, transaction);

            performTransfer(acc1, acc4, 50, transaction);
            performTransfer(acc3, acc4, 300, transaction);

            user1.printBalances();
            user2.printBalances();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    private static void performTransfer(BankAccount from, BankAccount to, double amount, Transiaction transaction) {
        if (transaction.transfer(from, to, amount)) {
            System.out.println("Transfer " + amount + " from " + from.getAccountNumber() + " to " + to.getAccountNumber() + "done");
        } else {
            System.out.println("Transfer " + amount + " from " + from.getAccountNumber() + " to " + to.getAccountNumber() + " failed");
        }
    }

}

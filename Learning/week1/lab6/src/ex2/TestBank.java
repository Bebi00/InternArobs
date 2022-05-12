package ex2;

import ex1.BankAccount;

public class TestBank {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("paul", 100);
        BankAccount bankAccount1 = new BankAccount("paul", 100.5);
        BankAccount bankAccount2 = new BankAccount("Deni", 100);

        Bank bank = new Bank();
        bank.addAccount("paul", 100);
        bank.addAccount("paul", 120);
        bank.addAccount("paula", 200);
        bank.addAccount("Deni", 10);
        bank.addAccount("Raul", 55);

        System.out.println("All Bank Accounts in ascending order of balance:");
        bank.printAccounts();

        System.out.println("All Bank Accounts with a balance in the interval [100;500]:");
        bank.printAccounts(100,500);

        System.out.println("Bank Account of the owner paul");
        System.out.println(bank.getAccount("paul"));
    }
}

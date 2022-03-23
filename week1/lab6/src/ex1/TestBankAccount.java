package ex1;

public class TestBankAccount {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("paul",100);
        BankAccount bankAccount1 = new BankAccount("paul",100.5);
        BankAccount bankAccount2 = new BankAccount("Deni",100);

        System.out.println(bankAccount.equals(bankAccount1));
        System.out.println(bankAccount1.equals(bankAccount1));
        System.out.println(bankAccount.equals(bankAccount2));

    }
}

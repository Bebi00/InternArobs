package ex1;

import java.util.Objects;

public class BankAccount {
    
    private String owner;
    private double balance;

    public BankAccount(String owner,double balance){
        this.balance = balance;
        this.owner = owner;
    }

    public void withdraw(double amount){
        if((this.balance - amount) >=0){
            this.balance -= amount;
        }
        System.out.println("Withdrew "+amount+" successfully");
    }

    public void deposit(double amount){
        this.balance += amount;
        System.out.println("Deposited "+amount+" successfully");
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof BankAccount bankAccount){
            return bankAccount.balance == this.balance && bankAccount.owner.equals(this.owner);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, balance);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
}

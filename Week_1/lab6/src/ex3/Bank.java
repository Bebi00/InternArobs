package ex3;

import ex1.BankAccount;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Bank {
    TreeSet<BankAccount> accounts = new TreeSet<>(new Comparator<BankAccount>() {
        @Override
        public int compare(BankAccount o1, BankAccount o2) {
            return Double.compare(o1.getBalance(), o2.getBalance());
        }
    });

    public void addAccount(String owner,double balance){
        accounts.add(new BankAccount(owner, balance));
    }

    public void printAccounts(){
        for(BankAccount bankAccount:accounts){
            System.out.println(bankAccount.toString());
        }
    }

    public void printAccounts(double minBalance, double maxBalance){
        for(BankAccount bankAccount:accounts){
            if(bankAccount.getBalance() >= minBalance && bankAccount.getBalance()<= maxBalance){
                System.out.println(bankAccount);
            }
        }
    }

    // only returns the first instance found.
    public BankAccount getAccount(String owner){
        for(BankAccount bankAccount:accounts) {
            if (bankAccount.getOwner().equals(owner)){
                return bankAccount;
            }
        }
        return null;
    }
}

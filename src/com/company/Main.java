package com.company;

public class Main {

    public static void main(String[] args) {
        var myAccount = new BankAccount();
        var yourAccount = new BankAccount(2000, 0.05f);
        myAccount.deposit(1000);
        myAccount.addInterest();
        yourAccount.addInterest();
        System.out.println("Your account (ID: " + yourAccount.getAccountID() + ") currently has a balance of " +
                yourAccount.checkBalance());
        var didSucceed = myAccount.withdraw(2000);
        if (didSucceed)
            System.out.println("Successfully made withdrawal.");
        else
            System.out.println("Could not withdraw, current balance (ID: " + myAccount.getAccountID() + ") is " + myAccount.checkBalance());
    }
}

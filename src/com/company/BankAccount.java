package com.company;

public class BankAccount {
    private double balance;
    private float interestRate;
    private int accountID;
    private static int nextID = 1000;

    public BankAccount() {
        interestRate = 0.02f;
        accountID = nextID;
        nextID++;
    }

    public BankAccount(double initialBalance, float initialRate) {
        balance = initialBalance;
        interestRate = initialRate;
        accountID = nextID;
        nextID++;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public boolean withdraw(double amount) {
        if (balance < amount)
            return false;
        balance -= amount;
        return true;
    }

    public double addInterest() {
        var interest = balance * interestRate;
        balance += interest;
        return balance;
    }

    public double checkBalance() {
        return balance;
    }

    public int getAccountID() {
        return accountID;
    }
}


package com.company;

import java.util.ArrayList;
import java.util.Optional;

public class Customer {
    private int customerID;
    private String name;
    private ArrayList<BankAccount> accounts;

    public Customer(String customerName, int taxID) {
        customerID = taxID;
        name = customerName;
        accounts = new ArrayList<BankAccount>();
    }

    public BankAccount openAccount(double initialBalance) {
        var newAccount = new BankAccount();
        newAccount.deposit(initialBalance);
        var didSucceed = accounts.add(newAccount);
        return newAccount;
    }

    public Optional<BankAccount> closeAccount(int accountNumber) {
        for(var currentAccount : accounts) {
            if(currentAccount.getAccountID() == accountNumber) {
                accounts.remove(currentAccount);
                return Optional.of(currentAccount);
            }
        }
        System.out.println("Tried to remove an account but it didn't exist.");
        return Optional.empty();
    }


    public String getName() {
        return name;
    }

    public int getID() {
        return customerID;
    }
}


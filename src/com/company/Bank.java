package com.company;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Bank {
    private ArrayList<BankAccount> allAccounts;
    private ArrayList<Customer> allCustomers;

    public Bank() {
        allAccounts = new ArrayList<BankAccount>();
        allCustomers = new ArrayList<Customer>();
    }

    public void doBanking() {
        var inputReader = new Scanner(System.in);
        while(true) {
            printMenu();
            var userChoice = inputReader.nextInt();
            switch (userChoice) {
                case 1:
                    System.exit(0);
                case 2:
                    addCustomer(inputReader);
                    break;
                case 3:
                    System.out.println("What is the customer's ID number?");
                    var idToFind = inputReader.nextInt();
                    var customer = getCustomer(idToFind);
                    if (customer.isPresent()) {
                        doCustomerMenu(inputReader, customer.get());
                    } else
                        System.out.println("No such customer exists at this bank.");
                    break;
            }
        }
    }

    private void doCustomerMenu(Scanner inputReader, Customer customer) {
        while(true) {
            printCustomerMenu();
            System.out.println("Enter Selection:");
            var choice = inputReader.nextInt();
            switch(choice) {
                case 1:
                    BankAccount newAccount = addAccountToCustomer(inputReader, customer);
                    allAccounts.add(newAccount);
                    break;
                case 2:
                    closeAccount(inputReader, customer);
                case 3:
                    return;
                default:
                    System.out.println("Please choose one of the choices from the list.");
            }
        }
    }

    private void closeAccount(Scanner inputReader, Customer customer) {
        System.out.println("What account number do you want to close?");
        var accountToClose = inputReader.nextInt();
        var closedAccount = customer.closeAccount(accountToClose);
        if(closedAccount.isPresent()) {
            System.out.println("Closing account...");
            allAccounts.remove(closedAccount.get());
        }
    }

    private BankAccount addAccountToCustomer(Scanner inputReader, Customer customer) {
        System.out.println("What is the account's initial balance?");
        var initialDeposit = inputReader.nextDouble();
        var newAccount = customer.openAccount(initialDeposit);
        System.out.println("Created new account with ID: " + newAccount.getAccountID());
        return newAccount;
    }

    private void printCustomerMenu() {
        System.out.println("###################################");
        System.out.println("Please select what to do with this customer.");
        System.out.println("     [1] Open Account");
        System.out.println("     [2] Close Account");
        System.out.println("     [3] Return to Main Menu");
        System.out.println("###################################");
    }

    private Optional<Customer> getCustomer(int customerID) {
        for(var currentCustomer : allCustomers) {
            if(currentCustomer.getID() == customerID)
                return Optional.of(currentCustomer);
        }
        return Optional.empty();
    }

    private void addCustomer(Scanner inputReader) {
        inputReader.nextLine(); //eats \n from previous call to nextInt
        System.out.print("Enter new Customer's name:");
        var newCustomerName = inputReader.nextLine();
        System.out.print("Enter new Customer's Tax ID (SSN):");
        var newCustomerTaxID = inputReader.nextInt();
        var newCustomer = new Customer(newCustomerName, newCustomerTaxID);
        allCustomers.add(newCustomer);
        System.out.println("Successfully added new Customer with Name: " + newCustomer.getName() +
                " and taxID: " + newCustomer.getID());
    }

    private void printMenu() {
        System.out.println("============================================");
        System.out.println("What do you want to do next?");
        System.out.println("    [1] Exit the program");
        System.out.println("    [2] Add a new Customer");
        System.out.println("    [3] Select Customer");
        System.out.println("============================================");
        System.out.println("Type the number of the options you want:");
    }
}


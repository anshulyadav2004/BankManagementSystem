package com.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "pin")
    private int pin;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "balance")
    private double balance;


    public Account() {}


    public Account(int accountNumber, int pin, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.customerName = customerName;
        this.balance = balance;
    }


    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public int getPin() { return pin; }
    public void setPin(int pin) { this.pin = pin; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}